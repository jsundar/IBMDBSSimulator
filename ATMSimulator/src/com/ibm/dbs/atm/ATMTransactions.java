package com.ibm.dbs.atm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMTransactions {
	
	// Map to hold the Cash in ATM Machine
	private Map<String,Integer> cashHolder = new HashMap<>();
	private List<Transactions> transactionList = new ArrayList<>();
	
	/***
	 * cash deposit methodwhich will accept only $10,$20,$50
	 * @param cashdeposit
	 * @return
	 */
	public void cashDeposit(int...cashdeposit) {
		int totalAmount = 0;
		for(int cash : cashdeposit) {
			
			if(cash == 10) {
				if(cashHolder.containsKey("10")) {
					int i = cashHolder.get("10");
					cashHolder.put("10", ++i);
				} else  {
					cashHolder.put("10", 1);
				}
				totalAmount += cash;
				System.out.println("Accepted $10");
			} else if(cash == 20) {
				if(cashHolder.containsKey("20")){
					int i = cashHolder.get("20");
					cashHolder.put("20", ++i);
				} else  {
					cashHolder.put("20", 1);
				}
				totalAmount += cash;
				System.out.println("Accepted $20");
			} else if(cash == 50) {
				if(cashHolder.containsKey("50")){
					int i = cashHolder.get("50");
					cashHolder.put("50", ++i);
				} else  {
					cashHolder.put("50", 1);
				}
				totalAmount += cash;
				System.out.println("Accepted $50");
			} else {
				System.err.println("Invalid denomination: " + cash +"$");
			}
		}
		transactionList.add(new Transactions(new Timestamp(System.currentTimeMillis()), "Credit", totalAmount, totalAmount));
	}
	
	/***
	 * withdraw cash from ATM 
	 * 3. Cash out is based on the availability of highest available denomination. E.g.
	 *	3.1 If machine has 50$, 20$, 20$, 20, $10$ and user enters 60$ to withdraw, currency dispensed will be 50$ and 10$.
	 *	3.2 If machine has 20$, 20$, $20, $10$, 10$, 10$ and user enters 60$ to withdraw, currency dispensed will be 20$, 20$, 20$.
	 *	3.3 If machine has 20$, 20$, $10, $10$, 10$, and user enters 60$ to	withdraw, currency dispensed will be 20$, 20$, 10$.
	 * @param withdrawAount
	 * @return int[] here assumption is [0]=>$50  ||  [1]=>$20  ||  [2]=>$10
	 */
	public int[] cashWithdrawal(int withdrawAount) {
		int[] withdrawCashHolder = new int[3];
		//if given withdrawal amount is not divisible by 10 then throw exception [Ask user to give another value]
		if(withdrawAount%10 != 0) {
			System.err.println("Insufficient Amount in ATM Machine.");
			return withdrawCashHolder;
			//throw new IllegalArgumentException("Please take only multipliers of $10");
			
		}
		
		if(getAvailableBalance() <= withdrawAount) {
			System.err.println("Insufficient Amount in ATM Machine.");
			return withdrawCashHolder;
		}
		
		int remainder = 0;
		int noOf50 = 0;
		int noOf20 = 0;
		int noOf10 = 0;
		
		if(withdrawAount >= 50) {
			noOf50 = withdrawAount / 50;
			remainder = withdrawAount % 50;
			int noOf50InCashHolder = cashHolder.get("50");
			int current50 = noOf50InCashHolder - noOf50;
			if(current50 > 0) {
				cashHolder.put("50", current50);
				
			} else {
				cashHolder.put("50", 0); // if cashholder does not have sufficient $50 then make it 0 and collect from $20/$10
				remainder = remainder + 50*(-current50);
				noOf50 = noOf50 + current50;
			}
		} else {
			remainder = withdrawAount;
		}
		
		if(remainder >= 20) { 
			noOf20 = remainder / 20;
			remainder = remainder % 20;
			int noOf20InCashHolder = cashHolder.get("20");
			int current20 = noOf20InCashHolder - noOf20;
			if(current20 > 0) {
				cashHolder.put("20", current20);
				
			} else {
				cashHolder.put("20", 0); // if cashholder does not have sufficient $20 then make it 0 and collect from $10
				remainder = remainder + 20*(-current20);
				noOf20 = noOf20 + current20;
			}
			
		} 
		
		if(remainder >= 10) { 
			noOf10 = remainder / 10;
			remainder = remainder % 10;
			int noOf10InCashHolder = cashHolder.get("10");
			int current10 = noOf10InCashHolder - noOf10;
			if(current10 > 0) {
				cashHolder.put("10", current10);
				
			} else {
				cashHolder.put("10", 0); 
				remainder = remainder + 10*(-current10);
				noOf10 = noOf10 + current10;
			}
			
		} 
		/*
		 * if(remainder > 0) {
		 * System.err.println("Insufficient Amount in ATM Machine."); throw new
		 * IllegalArgumentException(); }
		 */
		
		withdrawCashHolder[0] = noOf50;
		withdrawCashHolder[1] = noOf20;
		withdrawCashHolder[2] = noOf10;
		transactionList.add(new Transactions(new Timestamp(System.currentTimeMillis()), "Debit", withdrawAount, withdrawAount));
		return withdrawCashHolder;
	}
	
	public int getAvailableBalance() {
		int creditAmount = 0;
		int debitAmount = 0;
		for(Transactions trans : transactionList) {
			if(trans.getTransactionType().equals("Credit")) {
				creditAmount += trans.getAmount();
			}
			if(trans.getTransactionType().equals("Debit")) {
				debitAmount += trans.getAmount();
			}
		}
		return creditAmount - debitAmount;
	}
	
	public void miniStatement() {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Date Time Transaction Amount Closing Balance");
		System.out.println("-------------------------------------------------------------------");
		for(Transactions trans : transactionList) {
			System.out.println(trans.getCreatedDate() + "     " + trans.getTransactionType() + "      " + trans.getAmount() + "       "+ trans.getClosingBalance());
		}
	}
	
	public void loadATMMachine() {
		cashHolder.put("10", 100);
		cashHolder.put("20", 100);
		cashHolder.put("50", 100);
	}
	
	

}
