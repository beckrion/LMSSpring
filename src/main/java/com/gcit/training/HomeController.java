package com.gcit.training;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookCopiesDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.BookLoansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.GenreDAO;
import com.gcit.training.lms.dao.LibraryBranchDAO;
import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.LibraryBranch;
import com.gcit.training.lms.entity.Publisher;
import com.gcit.training.lms.service.AdministrativeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdministrativeService adminService;
	
	@Autowired
	AuthorDAO aDAO;
	
	@Autowired
	BookDAO bDAO;
	
	@Autowired
	BorrowerDAO boDAO;
	
	@Autowired
	LibraryBranchDAO lbDAO;
	
	@Autowired
	PublisherDAO pDAO;
	
	@Autowired
	GenreDAO geDAO;
	
	@Autowired
	BookCopiesDAO bcDAO;
	
	@Autowired
	BookLoansDAO blDAO;
	
	
	
	@RequestMapping(value = "/editauthor", method = {RequestMethod.GET,RequestMethod.POST})
	public String editorauthor(Locale locale, Model model) throws SQLException {
		return "editauthor";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		return "admin";
	}
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrower(Locale locale, Model model) {
		return "borrower";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addAuthor", method = {RequestMethod.GET,RequestMethod.POST})
	public String addAuthor() {
//	try {
//			String authorName = request.getParameter("authorName");
//			Author author = new Author();
//			author.setAuthorName(authorName);
//			aDAO.create(author);
//			return "addauthor";
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "Author add failed!";
//		}
//        
		return "addauthor";

	}
	@RequestMapping(value = "/addAuthor1", method = {RequestMethod.GET,RequestMethod.POST})
	public String runAddAuthor(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);
		aDAO.create(author);
		return "addauthor";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Author add failed!";
	}
		
	}
	@RequestMapping(value = "/viewAuthor", method = {RequestMethod.GET,RequestMethod.POST},produces="application/json")
	public String viewauthors(Locale locale, Model model) throws SQLException{
		model.addAttribute("authors",aDAO.readAll(1, 20));
		return "viewAuthor";
		//produces="application/json"
//		@PathVariable int PageNo,@PathVariable int PageSize

	}
	@RequestMapping(value = "/viewBorrower", method = {RequestMethod.GET,RequestMethod.POST},produces="application/json")
	public List<Borrower> viewBorrower() throws SQLException{
		return boDAO.readAll();
		//produces="application/json"

	}
	@RequestMapping(value = "/addBorrower", method = {RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	public String addBorrower(@RequestBody Borrower borrower)
	{
		try {
			boDAO.create(borrower);
			return "Borrower add!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Borrower add failed!";
		}
		
	}
	@RequestMapping(value = "/viewLibraryBranch", method = {RequestMethod.GET,RequestMethod.POST},produces="application/json")
	public List<LibraryBranch> viewLibraryBranch() throws SQLException{
		return lbDAO.readAll();
		//produces="application/json"
	}
	@RequestMapping(value = "/addLibraryBranch", method = {RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	public String addLibraryBranch(@RequestBody LibraryBranch libraryBranch)
	{
		try {
			lbDAO.create(libraryBranch);
			return "Library Branch add!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Library Branch add failed!";
		}
		
	}
	@RequestMapping(value = "/deleteAuthor", method = {RequestMethod.GET,RequestMethod.POST})
	public String delAuthor(HttpServletRequest request,Locale locale, Model model) {
		try {
			int author = Integer.parseInt(request.getParameter("authorId"));
			System.out.println(author);
			Author au = new Author();
			au.setAuthorId(author);
			aDAO.delete(au);
			return viewauthors(locale,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ttt";
		}
	}
	@RequestMapping(value = "/viewbook", method = {RequestMethod.GET,RequestMethod.POST})
	public String viewBook(Locale locale, Model model)
	{
		try {
			model.addAttribute("books",bDAO.readAll());
			return "viewbook";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}
	}
	@RequestMapping(value = "/addbook", method = {RequestMethod.GET,RequestMethod.POST})
	public String addBook(Locale local,Model model)
	{
		try {
			model.addAttribute("pub",pDAO.readAll());
			model.addAttribute("genre",geDAO.readAll());
			model.addAttribute("author",aDAO.readAll(0, 0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String[] pub = request.getParameterValues("pubC");
//		String[] gen = request.getParameterValues("genreC");
//		String[] aut = request.getParameterValues("auth");
		
		//System.out.println(pub.length+"  "+gen.length+"  "+aut.length);
		
		return "addbook";
	}
	@RequestMapping(value = "/addBook", method = {RequestMethod.GET,RequestMethod.POST})
	public String addBookAction(Locale local,Model model,HttpServletRequest request) throws NumberFormatException, SQLException
	{
		String[] pub = request.getParameterValues("pubC");
		String[] gen = request.getParameterValues("genreC");
		String[] aut = request.getParameterValues("auth");
		String title = request.getParameter("bookName");
		
		Book bk = new Book();
		Publisher pb = pDAO.readOne(Integer.parseInt(pub[0]));
		List<Genre> genre = new ArrayList<Genre>();
		List<Author> author = new ArrayList<Author>();
		
		bk.setTitle(title);
		bk.setPublisher(pb);
		for( int i=0;i<gen.length;i++)
		{
			//Genre tpGen = new Genre();
			System.out.println(gen[i]);
			Genre tpGen = geDAO.readOne(Integer.parseInt(gen[i]));
			genre.add(tpGen);
		}
		for( int i=0;i<aut.length;i++)
		{
			Author e = new Author();
			e = aDAO.readOne(Integer.parseInt(aut[i]));
			author.add(e);
		} 
		bk.setAuthors(author);
		bk.setGenre(genre);
		bDAO.addBook(bk);
		return viewBook(local,model);
	}
	@RequestMapping(value = "/updateAuthor", method = {RequestMethod.GET,RequestMethod.POST})
	public String editAuthor(Locale local,Model model,HttpServletRequest request)
	{
		int author = Integer.parseInt(request.getParameter("authorId"));
		String name = request.getParameter("authorName");
		Author au = new Author();
		au.setAuthorId(author);
		System.out.println(author);
		au.setAuthorName(name);
		System.out.println(name);
		try {
			aDAO.update(au);
			return viewauthors(local,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	@RequestMapping(value = "/chosebranch", method = RequestMethod.GET)
	public String chosebranch(Locale locale, Model model) {
		try {
			model.addAttribute("libInfo",lbDAO.readAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "chosebranch";
	}
	@RequestMapping(value = "/borrowBook", method = {RequestMethod.GET,RequestMethod.POST})
	public String borrowBook(Locale locale, Model model,HttpServletRequest request) throws SQLException
	{
		String[] branchInfo = request.getParameterValues("libB");
		LibraryBranch lb = null;
		List<BookCopies> bc = null;
		List<Book> bk = new ArrayList<Book>();
		try {
			lb = lbDAO.readOne(Integer.parseInt(branchInfo[0]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bc = bcDAO.readOneBrach(lb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(BookCopies bkcop : bc)
		{
			if(bkcop.getNoOfCopies()>0)
			{
				bk.add(bDAO.readOne(bkcop.getBook().getBookId()));
			}
			
		}
		model.addAttribute("bookList",bk);
		model.addAttribute("branchId",branchInfo[0]);
		
		return "borrbklist" ;
		
	}
	@RequestMapping(value = "/borrow", method = {RequestMethod.GET,RequestMethod.POST})
	public String borrow(Locale locale, Model model) throws SQLException {
		return "borrow";
	}
	@RequestMapping(value = "/updateBorrInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateBorrInfo(Locale locale, Model model,HttpServletRequest request) throws SQLException{
		int BookId = Integer.parseInt(request.getParameter("BookId"));
		int BranchId = Integer.parseInt(request.getParameter("BranchId"));
		int cardNo = Integer.parseInt(request.getParameter("borrowerNo"));
		String dateOut = request.getParameter("dateOut");
		String dateDue = request.getParameter("dateDue");
		BookLoans a = new BookLoans();
		a.setBook(bDAO.readOne(BookId));
		a.setBorrower(boDAO.readOne(cardNo));
		a.setBranch(lbDAO.readOne(BranchId));
		a.setDateOut(dateOut);
		a.setDueDate(dateDue);
		blDAO.borrow(a);
		BookCopies bc = new BookCopies();
		Book bk = new Book();
		bk.setBookId(BookId);
		bc.setBook(bk);
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchId(BranchId);
		bc.setBranch(lb);
		int noCop = bcDAO.readNumOfCop(bc);
		noCop--;
		bc.setNoOfCopies(noCop);
		bcDAO.updateNo(bc);
		return chosebranch(locale, model);

	}
	@RequestMapping(value = "/returnborno", method = {RequestMethod.GET,RequestMethod.POST})
	public String returnborno(Locale locale, Model model)
	{
		return "returnborno";
	}
	@RequestMapping(value = "/returnPage", method = {RequestMethod.GET,RequestMethod.POST})
	public String returnPage(Locale locale, Model model,HttpServletRequest request) throws SQLException
	{
		int borrNo = Integer.parseInt(request.getParameter("borrowNo"));

		BookLoans bkloan = new BookLoans();
		Borrower br = new Borrower();
		List<Book> bk = new ArrayList<Book>();
		br= boDAO.readOne(borrNo);
		List<LibraryBranch> branchId = new ArrayList<LibraryBranch>();
		

		bkloan.setBorrower(br);
		List<BookLoans> a =null;
		a = blDAO.readAll();
			
		for(BookLoans bklo : a)
		{
			System.out.println(bklo.getDateIn());
		if((bklo.getDateIn())==null||"".equals(bklo.getDateIn()))
		{
			bk.add(bklo.getBook());
			branchId.add(bklo.getBranch());
		}
		}
		model.addAttribute("bookList",bk);
		model.addAttribute("cardNo",borrNo);
		model.addAttribute("branchId",branchId);
		return "returnlist";
	}
	@RequestMapping(value = "/return", method = {RequestMethod.GET,RequestMethod.POST})
	public String returnl(Locale locale, Model model,HttpServletRequest request)
	{
		return "return";
	}
	//returnSubmit
	@RequestMapping(value = "/returnSubmit", method = {RequestMethod.GET,RequestMethod.POST})
	public String returnSubmit(Locale locale, Model model,HttpServletRequest request) throws SQLException
	{
		int borrNo = Integer.parseInt(request.getParameter("cardNo"));
		System.out.println(borrNo);
		BookLoans bkloan = new BookLoans();
		Borrower br = new Borrower();
		br= boDAO.readOne(borrNo);

		bkloan.setBorrower(br);
		
		int bookNo = Integer.parseInt(request.getParameter("bookId"));
		
		Book bk = bDAO.readOne(bookNo);
		bkloan.setBook(bk);
		
		String datein = request.getParameter("datein");
		bkloan.setDateIn(datein);
		blDAO.updateDateIn(bkloan);
		
		Integer branchId = Integer.parseInt(request.getParameter("branch"));
		System.out.println("++++++++"+branchId);
		LibraryBranch lb = new LibraryBranch();
		lb = lbDAO.readOne(branchId);
		BookCopies bkcp = new BookCopies();
		bkcp.setBook(bk);
		bkcp.setBranch(lb);
		int bkcopies = bcDAO.readNumOfCop(bkcp);
		bkcopies++;

		bkcp.setNoOfCopies(bkcopies);
		bcDAO.updateNo(bkcp);
		
		return returnborno(locale,model);
	}
	@RequestMapping(value = "/editbook", method = {RequestMethod.GET,RequestMethod.POST})
	public String editbook(Locale locale, Model model) 
	{
		return "editbook";
	}
	@RequestMapping(value = "/updateBook", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateBook(Locale locale, Model model,HttpServletRequest request) 
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String BookName = request.getParameter("bookName");
		Book a = new Book();
		a.setTitle(BookName);
		a.setBookId(bookId);
		try {
			bDAO.updateTitle(a);
			return viewBook(locale, model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		
	}
	@RequestMapping(value = "/viewpublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String viewpublisher(Locale locale, Model model)
	{
		try {
			model.addAttribute("books",pDAO.readAll());
			return "viewpublisher";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}
	}
	@RequestMapping(value = "/deletePublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String deletePublisher(HttpServletRequest request,Locale locale, Model model) {
		try {
			int pubId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher pub = new Publisher();
			pub.setPublisherId(pubId);
			pDAO.delete(pub);
			return viewpublisher(locale,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ttt";
		}
	}
	@RequestMapping(value = "/editPublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String editPublisher(Locale locale, Model model){
		return "editpublisher";
	}
	@RequestMapping(value = "/updatePublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String updatePublisher(Locale local,Model model,HttpServletRequest request)
	{
		int pub = Integer.parseInt(request.getParameter("publisherId"));
		String name = request.getParameter("publisherName");
		String publisherAdd = request.getParameter("publisherAdd");
		Publisher pu = new Publisher();
		pu.setPublisherId(pub);
		pu.setPublisherName(name);
		pu.setPublisherAddress(publisherAdd);
		try {
			pDAO.update(pu);
			return viewpublisher(local,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	@RequestMapping(value = "/addpublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String addpublisher(Locale locale, Model model){
		return "addpublisher";
	}
	@RequestMapping(value = "/addPublisher", method = {RequestMethod.GET,RequestMethod.POST})
	public String addPublisher(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
		String PubName = request.getParameter("pubName");
		String PubAdd = request.getParameter("pubAddr");
		Publisher Pub = new Publisher();
		Pub.setPublisherAddress(PubAdd);
		Pub.setPublisherName(PubName);
		pDAO.create(Pub);
		return "addpublisher";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Author add failed!";
	}
		
	}
	@RequestMapping(value = "/viewgenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String viewgenre(Locale locale, Model model)
	{
		try {
			model.addAttribute("genre",geDAO.readAll());
			return "viewgenre";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}
	}
	@RequestMapping(value = "/deleteGenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteGenre(HttpServletRequest request,Locale locale, Model model) {
		try {
			int genId = Integer.parseInt(request.getParameter("genreId"));
			Genre ge = new Genre();
			ge.setGenreId(genId);
			geDAO.delete(ge);
			return viewgenre(locale,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ttt";
		}
	}
	@RequestMapping(value = "/editGenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String editGenre(Locale locale, Model model){
		return "editgenre";
	}
	@RequestMapping(value = "/updateGenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateGenre(Locale local,Model model,HttpServletRequest request)
	{
		int genId = Integer.parseInt(request.getParameter("genreId"));
		String name = request.getParameter("genreName");
		Genre ge = new Genre();
		ge.setGenreId(genId);
		ge.setGenreName(name);
		try {
			geDAO.update(ge);
			return viewgenre(local,model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	@RequestMapping(value = "/addgenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String addgenre(Locale locale, Model model){
		return "addgenre";
	}
	@RequestMapping(value = "/addGenre", method = {RequestMethod.GET,RequestMethod.POST})
	public String addGenre(HttpServletRequest request, HttpServletResponse response) 
	{
		try {
		String genName = request.getParameter("genName");
		Genre gen = new Genre();
		gen.setGenreName(genName);
		geDAO.create(gen);
		return "addgenre";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Author add failed!";
	}
		
	}
	

}
