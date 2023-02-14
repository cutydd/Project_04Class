package chatbot;

public class Review {
	private String starSum;
	public String getStarSum() {
		return starSum;
	}
	public void setStarSum(String resultSet) {
		this.starSum = resultSet;
	}
	private double star;
	private String comments;
	
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
