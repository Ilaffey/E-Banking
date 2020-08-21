from flask import Flask
from flask import request, render_template, url_for, redirect
import CORBA, Bank
orb = CORBA.ORB_init(  )

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index_redirect():
    return redirect(url_for('index'))

@app.route('/account')
def index():
    return render_template('index.html', title = 'E-banking New Account Service')

@app.route('/account', methods=['POST'])
def create_account():
    try:
        email = str(request.form['email'])
        password = str(request.form['password'])
        account = str(request.form['account'])

        f = open("ild_key.txt", "r")
        idl_key = f.read()
        f.close()

        server_req = orb.string_to_object(idl_key)
        server_req = server_req._narrow(Bank.BankingServer)
        server_response = server_req.create_account(email, password, account)

        return render_template('index.html', title = 'E-banking New Account Service', message = server_response)
    except:
        return render_template('index.html', title = 'E-banking New Account Service', message = "E-banking New Account Service cannot be accessed")

if __name__ == '__main__':
    app.run(debug=True, port=4999)
