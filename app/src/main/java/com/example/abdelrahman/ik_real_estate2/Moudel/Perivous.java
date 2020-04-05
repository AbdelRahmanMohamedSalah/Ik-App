package com.example.abdelrahman.ik_real_estate2.Moudel;

import java.io.Serializable;

public class Perivous
  implements Serializable
{
  String afterPrevious;
  String clientMemberId;
  String clientName;
  String clientPhone;
  String code;
  String comment;
  String day;
  String memberName;
  String month;
  String ownerName;
  String ownerPhone;
  String periviewMemberId;
  String requestId;
  String state;
  String time;
  
  public Perivous() {}
  
  public Perivous(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.day = paramString1;
    this.month = paramString2;
    this.time = paramString3;
    this.code = paramString4;
  }
  
  public Perivous(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15)
  {
    this.ownerPhone = paramString1;
    this.ownerName = paramString2;
    this.clientPhone = paramString3;
    this.clientName = paramString4;
    this.day = paramString5;
    this.month = paramString6;
    this.time = paramString7;
    this.comment = paramString8;
    this.requestId = paramString9;
    this.periviewMemberId = paramString10;
    this.clientMemberId = paramString11;
    this.memberName = paramString12;
    this.afterPrevious = paramString13;
    this.code = paramString14;
    this.state = paramString15;
  }
  
  public String getAfterPrevious()
  {
    return this.afterPrevious;
  }
  
  public String getClientMemberId()
  {
    return this.clientMemberId;
  }
  
  public String getClientName()
  {
    return this.clientName;
  }
  
  public String getClientPhone()
  {
    return this.clientPhone;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public String getDay()
  {
    return this.day;
  }
  
  public String getMemberName()
  {
    return this.memberName;
  }
  
  public String getMonth()
  {
    return this.month;
  }
  
  public String getOwnerName()
  {
    return this.ownerName;
  }
  
  public String getOwnerPhone()
  {
    return this.ownerPhone;
  }
  
  public String getPeriviewMemberId()
  {
    return this.periviewMemberId;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public String getTime()
  {
    return this.time;
  }
  
  public void setAfterPrevious(String paramString)
  {
    this.afterPrevious = paramString;
  }
  
  public void setClientMemberId(String paramString)
  {
    this.clientMemberId = paramString;
  }
  
  public void setClientName(String paramString)
  {
    this.clientName = paramString;
  }
  
  public void setClientPhone(String paramString)
  {
    this.clientPhone = paramString;
  }
  
  public void setCode(String paramString)
  {
    this.code = paramString;
  }
  
  public void setComment(String paramString)
  {
    this.comment = paramString;
  }
  
  public void setDay(String paramString)
  {
    this.day = paramString;
  }
  
  public void setMemberName(String paramString)
  {
    this.memberName = paramString;
  }
  
  public void setMonth(String paramString)
  {
    this.month = paramString;
  }
  
  public void setOwnerName(String paramString)
  {
    this.ownerName = paramString;
  }
  
  public void setOwnerPhone(String paramString)
  {
    this.ownerPhone = paramString;
  }
  
  public void setPeriviewMemberId(String paramString)
  {
    this.periviewMemberId = paramString;
  }
  
  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }
  
  public void setState(String paramString)
  {
    this.state = paramString;
  }
  
  public void setTime(String paramString)
  {
    this.time = paramString;
  }
}


/* Location:              C:\Users\Abdel Rahman\Desktop\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\example\abdelrahman\ik_real_state\Moudel\Perivous.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */