package com.ibm.dbs.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ATMSimulator {
	public static void inputMenuItem() {
		System.out.println("Select your Option from below Menu item to perform your action.......");
		
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Display Balance");
		System.out.println("4. Mini Statement");
		System.out.println("5. Exit");

	}

	public static int getUserInput() {
		System.out.println("Enter the value 1 to 5");
		BufferedReader reader = null;
		int returnInteger;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String inputString = reader.readLine();
			int intValue = Integer.parseInt(inputString);
			System.out.println("Your Input value is : " + intValue);
			if(intValue > 0 && intValue < 6) {
				returnInteger = intValue;
			} else {
				System.out.println("You entered input values is not [1,2,3,4,5]");
				returnInteger = 0;
			}
			
		} catch (Exception e) {
			System.out.println("You entered input values is not a number.");
			returnInteger = 0;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return returnInteger;
	}
	
	public static int[] userInputForCashDeposite() {
		
		System.out.println("Please deposite only $10, $20,$50 ONLY. You can use single space for next cash amount");
		BufferedReader reader = null;
		int[] cashDepositeArray = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String inputString = reader.readLine();
			System.out.println("your Input String is" + inputString);
			StringTokenizer tokens = new StringTokenizer(inputString, " ");
			cashDepositeArray = new int[tokens.countTokens()];
			int i =0;
			while(tokens.hasMoreElements()) {
				cashDepositeArray[i] = Integer.parseInt(tokens.nextToken());
			}
		} catch (Exception e) {
			System.out.println("You entered input values is not correct.");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cashDepositeArray;
	}
	
	public static int userInputForCashWithdraw() {
		System.out.println("Please take only multipliers of $10");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int withdrawAmount = 0;
		try {
			withdrawAmount = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("You entered input values is not correct.");
		} finally {
			in.close();
		}
		return withdrawAmount;
	}
	
	public void scenario1() {

		ATMTransactions transaction = new ATMTransactions();
		//load the ATM machine with Cash
		//transaction.loadATMMachine();
		
		//1.Cash Deposit with invalid denomination
		System.out.println("1.Cash Deposit with invalid denomination");
		System.out.println("1. User Inputs for Cash Deposit: 10,50,20,10,10,20,30,40,30" );
		transaction.cashDeposit(10,50,20,10,10,20,30,40,30);
		System.out.println("2. User Inputs for Cash Withdrawal: 40");
		int[] dispensing = transaction.cashWithdrawal(40);
		System.out.println("Dispensing $50 => " + dispensing[0]);
		System.out.println("Dispensing $20 => " + dispensing[1]);
		System.out.println("Dispensing $10 => " + dispensing[2]);
		System.out.println("3. Available balance:" + transaction.getAvailableBalance());
		System.out.println("4. Mini Statement : ");
		transaction.miniStatement();
		System.out.println("5. Exit.");
		System.out.println( "Have a good day" );
		
	}
	
	public void scenario2() {

		ATMTransactions transaction = new ATMTransactions();
		//load the ATM machine with Cash
		//transaction.loadATMMachine();
		
		//2. Cash Deposit with valid denomination
		System.out.println("2.Cash Deposit with valid denomination");
		System.out.println("1. User Inputs for Cash Deposit: 10,50,20,10,10,20,10,10" );
		transaction.cashDeposit(10,50,20,10,10,20,10,10);
		System.out.println("2. User Inputs for Cash Withdrawal: 80");
		int[] dispensing = transaction.cashWithdrawal(80);
		System.out.println("Dispensing $50 => " + dispensing[0]);
		System.out.println("Dispensing $20 => " + dispensing[1]);
		System.out.println("Dispensing $10 => " + dispensing[2]);
		System.out.println("3. Available balance:" + transaction.getAvailableBalance());
		System.out.println("4. Mini Statement : ");
		transaction.miniStatement();
		System.out.println("5. Exit.");
		System.out.println( "Have a good day" );
	}
	
	public void scenario3() {

		ATMTransactions transaction = new ATMTransactions();
		//load the ATM machine with Cash
		//transaction.loadATMMachine();
		
		//3. Withdraw amount which is insufficient amount in ATM
		System.out.println("3. Withdraw amount which is insufficient amount in ATM");
		System.out.println("1. User Inputs for Cash Deposit: 10,50,20,10,10,20,10,20,10,20,10,10,20,10,50,50" );
		transaction.cashDeposit(10,50,20,10,10,20,10,20,10,20,10,10,20,10,50,50);
		System.out.println("2. User Inputs for Cash Withdrawal: 400");
		int[] dispensing = transaction.cashWithdrawal(400);
		System.out.println("Dispensing $50 => " + dispensing[0]);
		System.out.println("Dispensing $20 => " + dispensing[1]);
		System.out.println("Dispensing $10 => " + dispensing[2]);
		System.out.println("3. Available balance:" + transaction.getAvailableBalance());
		System.out.println("4. Mini Statement : ");
		transaction.miniStatement();
		System.out.println("5. Exit.");
		System.out.println( "Have a good day" );
	}
	
	public static void main(String[] args) throws IOException {
		
		
			ATMSimulator simulator = new ATMSimulator();
			System.out.println("*******************************************************");
			simulator.scenario1();
			System.out.println("*******************************************************");
			simulator.scenario2();
			System.out.println("*******************************************************");
			simulator.scenario3();
			System.out.println("*******************************************************");
			
			
	}

}
