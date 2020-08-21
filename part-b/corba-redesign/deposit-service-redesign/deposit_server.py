import sys
import os
import sqlite3
import hashlib
import re

import CORBA, Deposit, Deposit__POA

class BankingServer(Deposit__POA.BankingServer):
    def deposit(self, email, password, account, amount):
        def valid_string(string):
            for i in range(len(string)):
                c = ord(string[i])
                if(c == 34 or c == 10 or c == 8 or c == 39 or c == 13 or c == 9 or c == 92):
                    return False;
            return True


        database_directory = os.path.join(os.path.dirname(os.path.realpath(__file__)), "../", '../', "ebanking.db")

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

        if amount <= 0:
            return "Cannot deposit no or a negative amount of money into the account"

        try:

            sqliteConnection = sqlite3.connect(database_directory)
            cursor = sqliteConnection.cursor()
            sqlite_select_query = """SELECT accounts.account_id as account_id, accounts.balance as balance, users.password as password FROM users INNER JOIN accounts ON users.user_id = accounts.user_id where users.email = '{email}' and accounts.accountType = '{account}';""".format(email = email, account = account)
            cursor.execute(sqlite_select_query)
            records = cursor.fetchall()

            row = None
            for record in records:
                row = record;
                break;
            
            if row is None:
                return 'Account not found'

            account_id, balance, database_password = row
            
            if password != database_password:
                return 'Invalid password'
            cursor.execute("UPDATE accounts set balance = balance + {amount} where account_id = '{account_id}'".format(amount=(amount * 100), account_id=account_id))
            sqliteConnection.commit()

            displayed_balance = ((float(balance))/100)+amount
            return "The updated balance of the account is " + str(displayed_balance)
            
        except Exception as e:
            print(e)
            return "An error occured"
        

orb = CORBA.ORB_init(sys.argv)
poa = orb.resolve_initial_references("RootPOA")

servant = BankingServer()
poa.activate_object(servant)

with open("ild_key.txt", "w") as f:
    f.write(orb.object_to_string(servant._this()))


poa._get_the_POAManager().activate(  )
orb.run(  )