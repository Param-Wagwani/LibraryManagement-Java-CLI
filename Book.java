import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private int bookId;

	public Book(String title, String author, int bookId){
		this.title = title;
		this.author = author;
		this.bookId = bookId;
	}

	public String getTitle(){ return this.title; }
	public String getAuthor() { return this.author; }
	public int getBookId() { return this.bookId; } 

	public void setTitle(String title) { this.title = title; }
	public void setAuthor(String Author) { this.author = author; }
	// public void setBookId(int bookId) { this.bookId = bookId; }

	public String toString(){
		return "Book [Title: " + title + ", Author: " + author + ", Book ID: " + bookId + "]";
	}
}	