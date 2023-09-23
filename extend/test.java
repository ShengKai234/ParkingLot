class test {
    public static void main(String[] args) {
        System.out.println("test....");
        String timeExit = "23:00";
        String timeEntry = "19:30";
        String[] exitTime = timeExit.split(":");
        String[] entryTime = timeEntry.split(":");
        Integer exitMin = Integer.parseInt(exitTime[0]) * 60 + Integer.parseInt(exitTime[1]);
        Integer entryMin = Integer.parseInt(entryTime[0]) * 60 + Integer.parseInt(entryTime[1]);
        System.out.println(exitMin);
        System.out.println(entryMin);
        double d = Math.ceil((double) (exitMin - entryMin) / 60);
        System.out.println((int) d);

    }
}