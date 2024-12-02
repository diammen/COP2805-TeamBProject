// Brianna Hart
package teamb;

public class Seat {  
   private int seatId;  
   private int seatNumber;  
   private int rowNumber;  
   private int theaterId;  
  
   public Seat(int seatId, int seatNumber, int rowNumber, int theaterId) {  
      this.seatId = seatId;  
      this.seatNumber = seatNumber;  
      this.rowNumber = rowNumber;  
      this.theaterId = theaterId;  
   }  
  
   public int getSeatId() {  
      return seatId;  
   }  
  
   public void setSeatId(int seatId) {  
      this.seatId = seatId;  
   }  
  
   public int getSeatNumber() {  
      return seatNumber;  
   }  
  
   public void setSeatNumber(int seatNumber) {  
      this.seatNumber = seatNumber;  
   }  
  
   public int getRowNumber() {  
      return rowNumber;  
   }  
  
   public void setRowNumber(int rowNumber) {  
      this.rowNumber = rowNumber;  
   }  
  
   public int getTheaterId() {  
      return theaterId;  
   }  
  
   public void setTheaterId(int theaterId) {  
      this.theaterId = theaterId;  
   }  
  
   @Override  
   public String toString() {  
      return "Seat{" +  
           "seatId=" + seatId +  
           ", seatNumber=" + seatNumber +  
           ", rowNumber=" + rowNumber +  
           ", theaterId=" + theaterId +  
           '}';  
   }  
}  