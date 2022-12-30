package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.Area;

public class CopyRecord {

    String time;
    String operation;
    String adminName;
    String userName;
    Area area;

    public CopyRecord(){}

    public CopyRecord(String time, String operation, String adminName,String userName,Area area){
        this.time = time;
        this.operation = operation;
        this.adminName = adminName;
        this.userName = userName;
        this.area = area;
    }

    public String getTime(){return this.time;}

    public void setTime(String time){this.time = time;}

    public String getOperation(){return  this.operation;}

    public void setOperation(String operation){this.operation = operation;}

    public String getAdminName(){return this.adminName;}

    public void setAdminName(String adminName){this.adminName = adminName;}

    public String getUserName(){return this.userName;}

    public void setUserName(String userName){this.userName = userName;}

    public Area getArea(){return this.area;}

    public void setArea(Area area){this.area = area;}
}
