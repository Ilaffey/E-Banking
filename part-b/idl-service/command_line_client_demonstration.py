import CORBA, Balance
orb = CORBA.ORB_init(  )

f = open("ild_key.txt", "r")
idl_key = f.read()
f.close()

email = "ian@gmail.com"
password = "1"
account = "1"
o = orb.string_to_object(idl_key)
o = o._narrow(Balance.BankingServer)
print o.get_balance(email, password, account)
