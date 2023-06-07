package com.filim.program;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.filim.program.dao.CustomerDAO;
import com.filim.program.dto.Customers;
import com.filim.program.dto.MovieDetails;
import com.filim.program.dto.OrderHistory;
import com.filim.program.dto.Seats;

@Controller
public class MainController 
{
	@Autowired
	 private CustomerDAO dao;
	
	
	@GetMapping("/")
	public String homePage(Model m, HttpSession session)
	{
		String movie = (String) session.getAttribute("movieName");
		System.out.println(movie + "!=!=!=!=!=!=Index");
		List<MovieDetails> movie1 = dao.getAllMovie();
		m.addAttribute("movieList", movie1);
		m.addAttribute("menu", "home");

		return "index";
	}
	
//-------------------------------------------------------------------------------------------------------------------------//
	
	@GetMapping("/booking")
	public String bookingCheck(@RequestParam("movieName") String movieName, Model m, HttpSession session) {
		List<MovieDetails> movie1 = dao.getAllMovie();
		System.out.println(dao);
		System.out.println(movieName);
		System.out.println(movie1);
		List<String> checkMovie = new ArrayList<>();
		for (MovieDetails str : movie1) 
		{
			checkMovie.add(str.getMovieName());
		}
		if (checkMovie.contains(movieName)) 
		{
			session.setAttribute("moviename", movieName);
			System.out.println(movieName);
			LocalDate now = LocalDate.now();
			LocalDate monthLimit = LocalDate.now();
			String time = "09:00 am";
			List<String> seatNo1 = new ArrayList<String>();
			List<Seats> all = dao.getAllSeat(now, time);

			for (Seats s : all) 
			{
				for (String s1 : s.getsNo()) 
				{
					seatNo1.add(s1);
				}

			}

			m.addAttribute("date", now);
			m.addAttribute("max", monthLimit.plusMonths(1));
			m.addAttribute("min", monthLimit);
			m.addAttribute("time", time);
			m.addAttribute("seats", seatNo1);
			
			return "home";

		   } 
		else 
		{
			return "redirect:/";

		}

	}
	
	@GetMapping("/serching")
	public String serchMovie(String movieName, Model mo,HttpSession session)
	{
		List<MovieDetails> m1=dao.getAllMovie();
		ArrayList<String> l1=new ArrayList<String>();
		for (MovieDetails details : m1) 
		{
			l1.add(details.getMovieName());
		}
		if (l1.contains(movieName)) 
		{
			session.setAttribute("movieName", movieName);
			System.out.println(movieName);
			
			return "home" ;
		}
		else 
		{
			return "redirect:/";
		}
			
		
		}
		
//----------------------------------------------------------------------------------------------------------------------//
		
	@GetMapping("/register")
	public String register(Model mo)
	{

		mo.addAttribute("menu", "register");
		return "register";
	}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/loginForm")
		public String loginForm(Model mo) 
		{
			mo.addAttribute("menu", "login");
			return "login";
		}
//----------------------------------------------------------------------------------------------------------------------//
		@PostMapping("/save")
		public String save(@ModelAttribute("customer") Customers customer)
		{
			dao.customerSave(customer);
			return "redirect:/register";

		}

//----------------------------------------------------------------------------------------------------------------------//
		@PostMapping("/processing")
		public String login(@RequestParam("email") String email, @RequestParam("password") String password,
				HttpSession session, Model m) {

			Customers object = (Customers) session.getAttribute("user");
			if (object != null) {
				return "redirect:/booking-seat";
			} else {

				Customers customer = dao.loginProsesing(email, password);

				if (customer == null) {
					m.addAttribute("failed", "Invalied login");
					return "login";
				} else {
					session.setAttribute("user", customer);
				}
				return "redirect:/home";
			}
		}
//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/home")
		public String mainDashboard(HttpSession session, Model mo) {
			session.removeAttribute("bookingdate");
			session.removeAttribute("bookingtime");
			session.removeAttribute("movieName");
			mo.addAttribute("menu", "home");

			String message = (String) session.getAttribute("msg");
			mo.addAttribute("message", message);
			session.removeAttribute("msg");
			List<MovieDetails> movie2 = dao.getAllMovie();
			mo.addAttribute("listMovie", movie2);

			return "main-dashboard";
		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/booking-seat")
		public String getUser(@RequestParam("movieName") String movieName, HttpSession session, Model mo) {
			List<MovieDetails> movie2 = dao.getAllMovie();
			List<String> checkMovie = new ArrayList<>();
			for (MovieDetails string : movie2) {
				checkMovie.add(string.getMovieName());
			}
			if (checkMovie.contains(movieName)) {
				session.setAttribute("movieName", movieName);

				LocalDate now = LocalDate.now();
				LocalDate monthLimit = LocalDate.now();
				String time = "09:00 am";

				Customers customer = (Customers) session.getAttribute("user");
				List<String> seatNo1 = new ArrayList<String>();
				List<Seats> seat = customer.getSeat();

				List<Seats> all = dao.getAllSeat(now, time);

				for (Seats s : all) {
					for (String s1 : s.getsNo()) {
						seatNo1.add(s1);
					}

				}

				mo.addAttribute("date", now);
				mo.addAttribute("time", time);
				mo.addAttribute("max", monthLimit.plusMonths(1));
				mo.addAttribute("min", monthLimit);
				mo.addAttribute("seats", seatNo1);
				mo.addAttribute("seat", seat);
				session.setAttribute("user", customer);
				return "dashboard";
			} else {
				return "redirect:/home";
			}

		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.removeAttribute("user");

			session.removeAttribute("bookingdate");
			session.removeAttribute("bookingtime");
			session.removeAttribute("movieName");

			return "redirect:/";
		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/book-seat")
		public String bookSeat(@ModelAttribute("Seat") Seats seat, @RequestParam("movieName") String movieName,
				HttpSession session, Model mo) 
		{
			LocalDate currentDate = LocalDate.now();
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date todayDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());
			LocalDate date = (LocalDate) session.getAttribute("bookingdate");
			String time = (String) session.getAttribute("bookingtime");
			System.out.println(seat.getsNo().equals(null) + " wooo" + movieName.equals(null));
			Customers object = (Customers) session.getAttribute("user");

			if (object == null) {
				return "redirect:/loginForm";
			} else if ((seat.getsNo().isEmpty()) && (movieName.equals(null))) {
				System.out.println("Seat is null");
				return "redirect:/home";
			} else if (date == null) {
				date = currentDate;
				time = "09:00 am";
				if (((date.isAfter(currentDate)) || (date.equals(currentDate)))
						&& (date.isBefore(currentDate.plusMonths(1)) || date.equals(currentDate.plusMonths(1)))) {

					Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
					List<Double> price = new ArrayList<Double>();
					double sum = 0;
					double p = 250.22d;
					for (String s : seat.getsNo()) {
						sum = sum + p;
						price.add(p);
					}
					seat.setTotal(sum);
					seat.setSprice(price);

					OrderHistory history = new OrderHistory(seat.getsNo(), price, sum, movieName, todayDate, date2, time,
							object);
					dao.seatSave(seat, object, date2, time);
					dao.historySave(history, object);
					List<String> seatNo1 = new ArrayList<String>();
					List<Customers> all = dao.getAll();
					for (Customers c : all) {
						for (Seats s : c.getSeat()) {
							for (String s1 : s.getsNo()) {
								seatNo1.add(s1);
							}

						}
					}

					mo.addAttribute("seats", seatNo1);
					session.setAttribute("user", object);
					session.setAttribute("msg", "your seat book successsfully");
					return "redirect:/home";

				} else {
					System.out.println("ye date current date se pahle ki date hai");
					return "redirect:/booking-seat?movieName=" + movieName;

				}
			} else {
				if (((date.isAfter(currentDate)) || (date.equals(currentDate)))
						&& (date.isBefore(currentDate.plusMonths(1)) || date.equals(currentDate.plusMonths(1)))) {
					Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
					List<Double> price = new ArrayList<Double>();
					double sum = 0;
					double p = 525.22d;
					for (String s : seat.getsNo()) {
						sum = sum + p;
						price.add(p);
					}
					seat.setTotal(sum);
					seat.setSprice(price);

					OrderHistory history = new OrderHistory(seat.getsNo(), price, sum, movieName, todayDate, date2, time,
							object);
					dao.seatSave(seat, object, date2, time);
					dao.historySave(history, object);
					List<String> seatNo1 = new ArrayList<String>();
					List<Customers> all = dao.getAll();
					for (Customers c : all) {
						for (Seats s : c.getSeat()) {
							for (String s1 : s.getsNo()) {
								seatNo1.add(s1);
							}

						}
					}

					mo.addAttribute("seats", seatNo1);
					session.setAttribute("user", object);
					session.setAttribute("msg", "your seat book successsfully");
					return "redirect:/home";

				} else {
					System.out.println("ye date current date se pahle ki date hai");
					return "redirect:/booking-seat?movieName=" + movieName;

				}
			}

		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/order-history")
		public String allHistory(HttpSession session, Model mo)
		{
			Date todayDate = new Date();
			Customers obj = (Customers) session.getAttribute("user");
			session.setAttribute("user", obj);
			List<OrderHistory> list = dao.getAllHistory(obj.getcId());
			mo.addAttribute("List", list);
			mo.addAttribute("todaydate", todayDate);

			LocalDate date = (LocalDate) session.getAttribute("bookingdate");
			System.out.println(date);
			mo.addAttribute("menu", "order");
			return "history";
		}
		//@PostMapping("/adminLogin")
		//public String adminLogin(@RequestParam("email") String email,@RequestParam("password")String password,HttpSession session,Model mo)
		//{
		//	if("admin@gamil.com".equals(email)&&"123".equals(password))
		//	{
			//	session.setAttribute("email",email);
		//	}
			
		//	return "login";
			
		//}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/clear-seats")
		public String eraseSeat(HttpSession session) {
			LocalDate now = LocalDate.now();
			String time = "09:00 am";
			Customers object = (Customers) session.getAttribute("user");

			if (object != null) {
				List<Seats> list = dao.getAllSeat(now, time);
				for (Seats seat : list) {
					long id = seat.getsId();
					dao.delete(id);
				}

			}
			return "redirect:/booking-seat";
		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/all-customers-records")
		public String allRecords(Model m, HttpSession session) {
			Customers object = (Customers) session.getAttribute("user");
			long bid = object.getcId();
			if (bid == 1) {
				List<Customers> all = dao.getAll();
				m.addAttribute("records", all);
				m.addAttribute("menu", "allusers");
				return "user_records";
			} else {
				return "redirect:/booking-seat";
			}
		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/all-seats/{id}")
		public String allSeats(@PathVariable("id") long id, Model m, HttpSession session) {
			Customers object = (Customers) session.getAttribute("user");
			long bid = object.getcId();
			if (bid == 1) {
				List<OrderHistory> list = dao.getAllHistory(bid);
				m.addAttribute("seatRecords", list);
				m.addAttribute("menu", "allusers");
				return "seat-records";
			} else {
				return "redirect:/booking-seat";
			}

		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/setting")
		public String getSetting(Model m, HttpSession session) {
			Customers customer = (Customers) session.getAttribute("user");
			m.addAttribute("user", customer);
			m.addAttribute("menu", "setting");
			return "setting";
		}

//----------------------------------------------------------------------------------------------------------------------//
		@GetMapping("/setting/update/{id}")
		public String updateForm(@PathVariable("id") long id, Model m) {
			System.out.println(id);
			m.addAttribute("menu", "setting");
			return "update-details";

		}

//----------------------------------------------------------------------------------------------------------------------//
		@PostMapping("/setting/update-details")
		public String updateDetails(@ModelAttribute("customer") Customers cust, HttpSession session) {
			String name = cust.getcName();
			String email = cust.getEmail();
			Customers customer = (Customers) session.getAttribute("user");
			customer.setcName(name);
			customer.setEmail(email);
			dao.updateDetail(customer);

			return "redirect:/setting";

		}
//----------------------------------------------------------------------------------------------------------------------//
		@PostMapping("/check")
		public String checkDate(@RequestParam("localdate") String date, @RequestParam("localtime") String time, Model m,
				HttpSession session) {
			Customers object = (Customers) session.getAttribute("user");
			String movie = (String) session.getAttribute("movieName");
			LocalDate monthLimit = LocalDate.now();
			if (movie.equals(null)) {
				return "home";

			} else if (object == null) {
				LocalDate now = LocalDate.parse(date);
				List<String> seatNo1 = new ArrayList<String>();
				List<Seats> all = dao.getAllSeat(now, time);

				for (Seats s : all) {
					for (String s1 : s.getsNo()) {
						seatNo1.add(s1);
					}

				}

				session.setAttribute("bookingdate", now);
				session.setAttribute("bookingtime", time);
				m.addAttribute("date", now);
				m.addAttribute("max", monthLimit.plusMonths(1));
				m.addAttribute("min", monthLimit);
				m.addAttribute("time", time);
				m.addAttribute("seats", seatNo1);

				return "home";
			} else {
				LocalDate now = LocalDate.parse(date);
				List<String> seatNo1 = new ArrayList<String>();
				List<Seats> all = dao.getAllSeat(now, time);

				for (Seats s : all) {
					for (String s1 : s.getsNo()) {
						seatNo1.add(s1);
					}

				}

				session.setAttribute("bookingdate", now);
				session.setAttribute("bookingtime", time);
				m.addAttribute("date", now);
				m.addAttribute("max", monthLimit.plusMonths(1));
				m.addAttribute("min", monthLimit);
				m.addAttribute("time", time);
				m.addAttribute("seats", seatNo1);

				return "dashboard";
			}

		}
		@GetMapping("/aboutus")
		public String aboutus(Model m)
		{
			m.addAttribute("menu", "about");
			return "aboutus";
			
		}
		
		@GetMapping("/contactus")
		public String contactus(Model m)
		{
			
			m.addAttribute("menu", "contact");
			return "contactus";
			
		}
		
		
		@ExceptionHandler(Exception.class)
		public String handleError(Exception ex, Model m, HttpSession session) {
			Customers object = (Customers) session.getAttribute("user");
			if (object == null) {
				return "redirect:/loginForm";
			} else {
				return "redirect:/home";
			}
		}


	}

	 

