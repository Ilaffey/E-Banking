from flask import Flask
from flask import request, render_template, url_for, redirect
import CORBA, Deposit
orb = CORBA.ORB_init(  )

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index_redirect():
    return redirect(url_for('index'))

@app.route('/', methods=['POST'])
def withdraw_redirect():
    return redirect(url_for('deposit'))

@app.route('/deposit')
def index():
    return render_template('index.html', title = 'E-banking Deposit Service')

@app.route('/deposit', methods=['POST'])
def deposit():
    try:
        email = str(request.form['email'])
        password = str(request.form['password'])
        account = str(request.form['account'])
        amount = int(request.form['amount']) if request.form['amount'].isnumeric() else 0

        f = open("ild_key.txt", "r")
        idl_key = f.read()
        f.close()

        server_req = orb.string_to_object(idl_key)
        server_req = server_req._narrow(Deposit.BankingServer)
        server_response = server_req.deposit(email, password, account, amount)
        
        return render_template('index.html', title = 'E-banking Deposit Service', message = server_response)
    except:
        return render_template('index.html', title = 'E-banking Deposit Service', message = "E-banking Deposit Service cannot be accessed")


if __name__ == '__main__':
    app.run(debug=True, port=5002)