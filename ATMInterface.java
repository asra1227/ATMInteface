import java.util.Scanner;
class BankAccount{
    Scanner sc=new Scanner(System.in);
    String Name;
    String Username;
    String Password;
    String AccountNo;
    float Balance=10000f;
    int Transcations=0;
    String TransactionHistory="";
    public void register(){
        // Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Your Name: ");
        this.Name=sc.nextLine();
        System.out.println("\nEnter Your Username: ");
        this.Username=sc.nextLine();
        System.out.println("\nEnter Your Password: ");
        this.Password=sc.nextLine();
        System.out.println("\nEnter Your Account Number: ");
        this.AccountNo=sc.nextLine();
        System.out.println("\nRegistration Successful. Please Login to your Bank Account");
    }
    public boolean login(){
        boolean isLogin=false;
        Scanner sc=new Scanner(System.in);
        while(!isLogin){
            System.out.println("Enter Your UserName: ");
            String username=sc.nextLine();
            if(Username.equals(username)){
                while(!isLogin){
                    System.out.println("\nEnter Your Password: ");
                    String password=sc.nextLine();
                    if(Password.equals(password)){
                        System.out.println("\nLogin Successful");
                        isLogin=true;
                    }else{
                        System.out.println("\nIncorrect Password. Please enter correct password");
                    }
                }
            }else{
                System.out.println("\nUsername not found. Please enter correct Username");
            }
        }
        return isLogin;
    }
    public void withdraw(){
        System.out.println("\nEnter Amount to withdraw: ");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try{
            if(Balance>=amount){
                Transcations++;
                Balance-=amount;
                System.out.println("\nWithdral successful.");
                String str=amount+"Rs Withdrawn\n";
                TransactionHistory=TransactionHistory.concat(str);
            }else{
                System.out.println("\nInsufficient Balance.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deposit(){
        System.out.println("\nEnter Amount to Deposit: ");
        // Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try{
            if(amount<=10000){
                Transcations++;
                Balance+=amount;
                System.out.println("\nDeposit successful.");
                String str=amount+"Rs Deposited\n";
                TransactionHistory=TransactionHistory.concat(str);
            }else{
                System.out.println("\nSorry. The limit is 10000.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void transfer(){
        // Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Receipent's Name: ");
        String receipent=sc.nextLine();
        System.out.println("Enter Amount to transfer: ");
        float amount=sc.nextFloat();
        sc.nextLine();
        try{
            if(amount<=50000){
                if(Balance>=amount){
                  Transcations++;
                  Balance-=amount;
                  System.out.println("\nSuccessfully Transferred to: "+receipent);
                  String str=amount+" Rs transferred to: "+receipent+"\n";
                  TransactionHistory=TransactionHistory.concat(str);
                }else{
                  System.out.println("\nSorry. The limit is 50000.");
                }
            }else{
                System.out.println("\nInsufficient Balance.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void checkBalance(){
        System.out.println("\n"+Balance+" Rs");
    }
    public void transHistory(){
        if(Transcations==0){
            System.out.println("No Transactions Happend");
        }else{
            System.out.println("\n"+TransactionHistory);
        }
    }
}
class ATMInterface{
    public static int takenIntegerInput(int limit){
        int input=0;
        boolean flag=false;
        Scanner sc=new Scanner(System.in);
        while(!flag){
            try{
                input=sc.nextInt();
                flag=true;
                if(flag&&input>limit||input<1){
                    System.out.println("Choose the number between 1 to "+limit);
                    flag=false;
                }
            }catch(Exception e){
                System.out.println("Enter only integer value.");
                sc.next();
                flag=false;
            }
        }
        return input;
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        try{
          System.out.println("\n********************WELCOME TO SM ATM INTERFACE********************");
          System.out.println("\n1.Register \n2.Exit");
          System.out.println("Choose one option: ");
          int Choose=takenIntegerInput(2);
          if(Choose==1){
              BankAccount b=new BankAccount();
              b.register();
              while(true){
                  System.out.println("\n1.Login \n2.Exit");
                  System.out.println("Enter Your Choice: ");
                  int ch=takenIntegerInput(2);
                  if(ch==1){
                      if(b.login()){
                          System.out.println("\n********************WELCOME BACK "+ b.Name+"********************");
                          boolean isFinished=false;
                          while(!isFinished){
                              System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance\n5.Transaction History \n6.Exit");
                              System.out.println("Enter Your Choice: ");
                              int choice=takenIntegerInput(6);
                              switch (choice) {
                                  case 1:
                                      b.withdraw();
                                      break;
                                  case 2:
                                      b.deposit();
                                      break;
                                  case 3:
                                      b.transfer();
                                      break;
                                  case 4:
                                      b.checkBalance();
                                      break;
                                  case 5:
                                      b.transHistory();
                                      break;
                                  case 6:
                                      isFinished=true;
                                      break;
                                } 
                           }
                        }
                    }else{
                       System.exit(0);
                    }
                }
            }else{
               System.exit(0);
            }
        }finally{
            sc.close();
        }
    }
}


