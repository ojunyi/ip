public class Events extends Task {

    protected String startDate;
    protected String endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + startDate + " To: " + endDate + ")";
    }
}
