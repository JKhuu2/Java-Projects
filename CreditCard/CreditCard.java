
public class CreditCard {
	private Money balance;		//the current balance
	private Money creditLimit;	//the approved credit limit
	private Person owner;		//the owner of the credit card

	//Constructor creates a credit card for the Person parameter
	//with an approved credit limit of the Money parameter
	//and sets the balance to a Money amount of zero 
	public CreditCard(Person newCardholder, Money limit) 
	{
		//MUST COMPLETE THIS
		this.owner=newCardholder;
		this.creditLimit=limit;
		this.balance=new Money(0.0);
		
	}

	//Accessor method returns the balance
	public Money getBalance()
	{
		//MUST COMPLETE THIS
		return this.balance;
	}

	//Accessor method returns the credit limit
	public Money getCreditLimit()
	{
		//MUST COMPLETE THIS
		return this.creditLimit;
	}

	//Accessor method returns information about the owner
	public String getPersonals()
	{
		return "Owner: " + this.owner;
		//MUST COMPLETE THIS
		
	}
	
	//Method to make a charge to the credit card (but only if
	//the credit limit will not be exceeded)
	public void charge(Money amount)
	{
		//MUST COMPLETE THIS
		this.balance=this.balance.add(amount);
		if(this.balance.compareTo(this.creditLimit)==-1) {
			
		}
		if (this.balance.compareTo(this.creditLimit)==1) {
			System.out.println("Balance exceeds credit limit");
			this.balance=this.balance.subtract(amount);
		}
		
	}

	//Method to make a payment to the credit card
	public void payment(Money amount)
	{
		//MUST COMPLETE THIS
		this.balance=this.balance.subtract(amount);
	}		
}
