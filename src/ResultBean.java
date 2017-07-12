
public class ResultBean { 
	
	private String title;
	private String isbn;
	private String authorname;
	private int availabilty;
	private int loan_id;
	private String fname;
	private String lname;
	private int card_id;
	private String date_out;
	private String due_date;
	private String date_in;
	private float fine;
	private boolean paid;
	private float totalAmt;
	
	
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public float getFine() {
		return fine;
	}
	public void setFine(float fine) {
		this.fine = fine;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getDate_out() {
		return date_out;
	}
	public void setDate_out(String date_out) {
		this.date_out = date_out;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getDate_in() {
		return date_in;
	}
	public void setDate_in(String date_in) {
		this.date_in = date_in;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public int getAvailabilty() {
		return availabilty;
	}
	public void setAvailabilty(int availabilty) {
		this.availabilty = availabilty;
	}
	

}
