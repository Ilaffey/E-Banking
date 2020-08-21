from flask import Flask
from flask import request, render_template, url_for, redirect
import CORBA, Balance
orb = CORBA.ORB_init(  )

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index_redirect():
    return redirect(url_for('index'))

@app.route('/', methods=['POST'])
def get_balance_redirect():
    return redirect(url_for('get_balance'))

@app.route('/balance')
def index():
    return render_template('index.html', title = 'E-banking Balance Inquiry Service')

@app.route('/balance', methods=['POST'])
def get_balance():
    try:
        email = str(request.form['email'])
        password = str(request.form['password'])
        account = str(request.form['account'])

        f = open("ild_key.txt", "r")
        idl_key = f.read()
        f.close()

        server_req = orb.string_to_object(idl_key)
        server_req = server_req._narrow(Balance.BankingServer)
        server_response = server_req.get_balance(email, password, account)
        
        return render_template('index.html', title = 'E-banking Balance Inquiry Service', message = server_response)
    except:
        return render_template('index.html', title = 'E-banking Balance Inquiry Service', message = "E-banking Balance Inquiry Service not available right now please try again later")


if __name__ == '__main__':
    app.run(debug=True, port=5001)