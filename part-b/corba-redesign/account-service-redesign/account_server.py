import sys
import os
import sqlite3
import hashlib
import re

import CORBA, Bank, Bank__POA

class BankingServer_i(Bank__POA.BankingServer):
    def create_account(self, email, password, account):
        def valid_string(string):
            for i in range(len(string)):
                c = ord(string[i])
                if(c == 34 or c == 10 or c == 8 or c == 39 or c == 13 or c == 9 or c == 92):
                    return False;
            return True


        database_directory = os.path.join(os.path.dirname(os.path.realpath(__file__)), "../", "../", "ebanking.db")

        if(email == None or email == ""):
            return "Please specify an email address"

        if(account == None or account == ""):
            return "Please specify an account type"

        if(password == None or password == ""):
            return "Please specify a password"

        email = email.lower();
        account = account.lower();
        password = hashlib.sha1(password).hexdigest()

        regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$".lower()
        
        if((not re.match(regex, email)) or (not valid_string(email))):
            return "Please specify a valid email address"
      
        if(not valid_string(account)):
            return "Please specify a valid account type"
      
        if(not valid_string(password)):
            return "Please specify a valid password"

        try:

            conn = sqlite3.connect(database_directory)
            cursor = conn.cursor()
            sqlite_select_query = """SELECT accounts.accountType as accountType, users.user_id as user_id, users.password as password FROM users LEFT JOIN accounts ON users.user_id = accounts.user_id where email = "{email}";""".format(email = email, account = account)
            cursor.execute(sqlite_select_query)
            records = cursor.fetchall()

            row_count = 0
            user_id = 0
            database_password = None
            valid_account = True
            responseMessage = ""

            for i in records:
                database_account, user_id, database_password = i
                row_count += 1
                if database_account == account:
                  valid_account = False

            newly_created = False
            records = []
            if(row_count <= 0):
                cursor.execute(
                    """INSERT INTO users (email, password) VALUES("{email}","{password}");""".format(email = email, password = password)
                )
                conn.commit()
                newly_created = True
                cursor.execute("""SELECT users.user_id as user_id FROM users where email = "{email}";""".format(email = email))
                records = cursor.fetchall()
                if len(records) > 0:
                    user_id = records[0][0]
            
            account_created = False
            if valid_account and user_id != 0:
                if database_password != password and (not newly_created):
                    cursor.close()
                    return "Invalid credentials"
                cursor.execute(
                    """INSERT INTO accounts (accountType, user_id) VALUES("{account}","{user_id}");""".format(account = account, user_id = user_id)
                )
                conn.commit()
                account_created = True


            cursor.close()
            if newly_created and account_created:
                return "A user with an email address of " + str(email) + " with a " + str(account) + " account has been created"
            elif account_created:
                return "A " + str(account) + " account has been created for " + str(email)
            elif newly_created:
                return "A user with an email address of " + str(email) + " has been created"
            else:
                return "A " + str(account) + " account already exists using the " + str(email) + " email address"

        except Exception as e:
            print(e)
            return "An error occured"
    

orb = CORBA.ORB_init(sys.argv)
poa = orb.resolve_initial_references("RootPOA")

servant = BankingServer_i(  )
poa.activate_object(servant)

with open("ild_key.txt", "w") as f:
    f.write(orb.object_to_string(servant._this()))


poa._get_the_POAManager().activate(  )
orb.run(  )