# E-Banking

Student Number: X12729231
Name: Ian Laffey

Create Account

Create Account, Desposit, Get Balance, Withdraw Dependancies:
1. Java 8
2. Node (installed with npm)
3. Bash (to run the start_server.sh)

Prerequisites:
1. Run the ./gradlew installDist and ./build/install/ebanking/bin/account-server commands to build and run the project and install the dependancies in the create_account folder
2. Run the npm install command in the create_account_gui folder to install the node dependancies

To run the project:
1. Open up a new bash shell
2. use the cd command to change directory into the folder where the "run_server.sh" script is and run the script
3. run the project by running "bash run_server.sh" command to run the create account service
4. Open up another new bash shell
5. use the cd command to change directory into the folder create_account_gui
6. install the projects dependancies by using the "npm install" command
7. run the project by running "npm start" command to run the create account gui
8. Follow steps 1-7 for each other service("desposit", "get-balance" and "withdraw")

Part B
CORBA & IDL Dependancies:
1. Python 2
2. grpc (installed with npm)
3. flask
4. CORBA (installed with pip - pip install CORBA)
5. omniorb(sudo apt-get install python-omniorb)
6. omniidl(sudo apt-get install-python)
7. Bash


To run the CORBA  create account project:
1. Open up a new bash shell
2. use the cd command to change directory into the folder(part-b/corba-redesign/account-service-redesign) where the "run_server.sh" script is and run the script
3. run the project by running "bash run_server.sh" command to run the create account service
4. Open up another new bash shell
5. run the GUI by running "bash run_gui.sh" command
6. Repeat steps 1-6 for other services, changing folders for each one. e.g balance-service-redesign, deposit-service-resign etc...

To run the IDL project:
1. Open up a new bash shell
2. use the cd command to change directory into the folder(part-b/idl-service) where the "compile_idle.sh" script is and install the projects dependancies using the requirements.txt file with pip
3. Compile the balance.idl by running the "compile_idle.sh" script
4. run the project by running "bash run_server.sh" command to run the IDL service
5. Open up another new bash shell
6. use the cd command to change directory into the folder where the "run_server.sh" script is
7. run the project by running "bash run_gui.sh" command to run the get balance gui