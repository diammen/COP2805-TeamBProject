package teamb;

import java.sql.*;

public class Showtime {  
   private int showtimeId;  
   private String showDateTime;  
   private int theaterId;  
   private int movieId;  
   private double price;  
  
   public Showtime(int showtimeId, String showDateTime, int theaterId, int movieId, double price) {  
      this.showtimeId = showtimeId;  
      this.showDateTime = showDateTime;  
      this.theaterId = theaterId;  
      this.movieId = movieId;  
      this.price = price;  
   }  
  
   public int getShowtimeId() {  
      return showtimeId;  
   }  
  
   public void setShowtimeId(int showtimeId) {  
      this.showtimeId = showtimeId;  
   }  
  
   public String getShowDateTime() {  
      return showDateTime;  
   }  
  
   public void setShowDateTime(String showDateTime) {  
      this.showDateTime = showDateTime;  
   }  
  
   public int getTheaterId() {  
      return theaterId;  
   }  
  
   public void setTheaterId(int theaterId) {  
      this.theaterId = theaterId;  
   }  
  
   public int getMovieId() {  
      return movieId;  
   }  
  
   public void setMovieId(int movieId) {  
      this.movieId = movieId;  
   }
   
   public String getMovieTitle() {
       String title = "";
       String query = """
                      SELECT title
                        FROM APP.Movie m
                        WHERE M.movie_id = 
                      """;
       
       try (Connection conn = Connecting.letConnect();
               PreparedStatement ps = conn.prepareStatement(title)) {
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return "";
   }
  
   public double getPrice() {  
      return price;  
   }  
  
   public void setPrice(double price) {  
      this.price = price;  
   }  
  
   @Override  
   public String toString() {  
      return "Showtime{" +  
           "showtimeId=" + showtimeId +  
           ", showDateTime='" + showDateTime + '\'' +  
           ", theaterId=" + theaterId +  
           ", movieId=" + movieId +  
           ", price=" + price +  
           '}';  
   }  
}