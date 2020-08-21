# Python stubs generated by omniidl from bank.idl
# DO NOT EDIT THIS FILE!

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA


_omnipy.checkVersion(4,2, __file__, 1)

try:
    property
except NameError:
    def property(*args):
        return None


#
# Start of module "Bank"
#
__name__ = "Bank"
_0_Bank = omniORB.openModule("Bank", r"bank.idl")
_0_Bank__POA = omniORB.openModule("Bank__POA", r"bank.idl")


# interface BankingServer
_0_Bank._d_BankingServer = (omniORB.tcInternal.tv_objref, "IDL:Bank/BankingServer:1.0", "BankingServer")
omniORB.typeMapping["IDL:Bank/BankingServer:1.0"] = _0_Bank._d_BankingServer
_0_Bank.BankingServer = omniORB.newEmptyClass()
class BankingServer :
    _NP_RepositoryId = _0_Bank._d_BankingServer[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_Bank.BankingServer = BankingServer
_0_Bank._tc_BankingServer = omniORB.tcInternal.createTypeCode(_0_Bank._d_BankingServer)
omniORB.registerType(BankingServer._NP_RepositoryId, _0_Bank._d_BankingServer, _0_Bank._tc_BankingServer)

# BankingServer operations and attributes
BankingServer._d_create_account = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), ((omniORB.tcInternal.tv_string,0), ), None)
BankingServer._d_get_balance = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), ((omniORB.tcInternal.tv_string,0), ), None)
BankingServer._d_deposit = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), omniORB.tcInternal.tv_octet), ((omniORB.tcInternal.tv_string,0), ), None)
BankingServer._d_withdraw = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), omniORB.tcInternal.tv_octet), ((omniORB.tcInternal.tv_string,0), ), None)

# BankingServer object reference
class _objref_BankingServer (CORBA.Object):
    _NP_RepositoryId = BankingServer._NP_RepositoryId

    def __init__(self, obj):
        CORBA.Object.__init__(self, obj)

    def create_account(self, *args):
        return self._obj.invoke("create_account", _0_Bank.BankingServer._d_create_account, args)

    def get_balance(self, *args):
        return self._obj.invoke("get_balance", _0_Bank.BankingServer._d_get_balance, args)

    def deposit(self, *args):
        return self._obj.invoke("deposit", _0_Bank.BankingServer._d_deposit, args)

    def withdraw(self, *args):
        return self._obj.invoke("withdraw", _0_Bank.BankingServer._d_withdraw, args)

omniORB.registerObjref(BankingServer._NP_RepositoryId, _objref_BankingServer)
_0_Bank._objref_BankingServer = _objref_BankingServer
del BankingServer, _objref_BankingServer

# BankingServer skeleton
__name__ = "Bank__POA"
class BankingServer (PortableServer.Servant):
    _NP_RepositoryId = _0_Bank.BankingServer._NP_RepositoryId


    _omni_op_d = {"create_account": _0_Bank.BankingServer._d_create_account, "get_balance": _0_Bank.BankingServer._d_get_balance, "deposit": _0_Bank.BankingServer._d_deposit, "withdraw": _0_Bank.BankingServer._d_withdraw}

BankingServer._omni_skeleton = BankingServer
_0_Bank__POA.BankingServer = BankingServer
omniORB.registerSkeleton(BankingServer._NP_RepositoryId, BankingServer)
del BankingServer
__name__ = "Bank"


__name__ = "bank_idl"

_exported_modules = ( "Bank", )

