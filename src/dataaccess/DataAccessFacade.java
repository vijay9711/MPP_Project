package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.checkoutRecord;
import dataaccess.DataAccessFacade.StorageType;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS;
	}
	// Windows user can use
	
	/*public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\dataaccess\\storage";*/
	
	// For Mac Users path can use / 
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "/src/dataaccess/storage";
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	public LibraryMember saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
		return member;
	}
	
	public void addNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String bookID = book.getIsbn();
		books.put(bookID, book);
		saveToStorage(StorageType.BOOKS, books);
	}
	
	public boolean addAuthor(Author author) {
		TestData ts = new TestData();
		ts.allAuthors.add(author);
		return true;
	}
	
	public Book getBook(String id) {
		HashMap<String,Book> booksList = readBooksMap();
		for(Entry<String, Book> item : booksList.entrySet()) {
			Book b = item.getValue();
			if(id.equals(b.getIsbn())) {
				System.out.println("Db data " + b.getCopyNums().toString());
				return b;
			}
		}
		return null;
	}



	public LibraryMember getMember(String id) {
		HashMap<String,LibraryMember> booksList = readMemberMap();
		for(Entry<String, LibraryMember> item : booksList.entrySet()) {
			LibraryMember b = item.getValue();
			if(id.equals(b.getMemberId())) {
				return b;
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}
	
	public List<checkoutRecord> getAllCheckout() {
		ArrayList<checkoutRecord> newArr = new ArrayList<checkoutRecord>();

		HashMap<String,LibraryMember> booksList = readMemberMap();
		for(Entry<String, LibraryMember> item : booksList.entrySet()) {
			LibraryMember b = item.getValue();
			if(b.getCheckoutRecord() != null && b.getCheckoutRecord().length > 0) {
				checkoutRecord[] record = b.getCheckoutRecord();
				for(int i = 0; i < record.length; i++) {
					newArr.add(record[i]);
				}
			}
		}
		return newArr;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	public String validateUser(String username, String password) {
		Map<String, User> ls = readUserMap();
		for (Entry<String, User> set :
             ls.entrySet()) {
            User un = set.getValue();
            if(username.equals(un.getId()) && password.equals(un.getPassword())) {
                System.out.println(set.toString());
            	return un.getAuthorization().toString();
            }
        }
		return "NA";
	}
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void loadAuthorMap(List<Author> authorList) {
		HashMap<String, Author> authorMap = new HashMap<String, Author>();
		authorList.forEach(author -> authorMap.put(author.getFirstName(), author));
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}

	
	
	
}
