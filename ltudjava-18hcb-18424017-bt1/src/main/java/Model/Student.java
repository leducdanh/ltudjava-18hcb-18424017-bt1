/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author danh
 */
public class Student {
    protected String _IdStudent;
    protected String _NAME;
    protected Boolean _GENDER; //True: Nam , False: Nu
    protected String _ID;

    public Student(){
    }
    
    public Student(String idSt, String name, boolean gender, String ID){
        this._IdStudent = idSt;
        this._NAME = name;
        this._GENDER = gender;
        this._ID = ID;
    }
    
    public String getIdStudent() {
        return _IdStudent;
    }

    public void setIdStudent(String _IdStudent) {
        this._IdStudent = _IdStudent;
    }

    public String getNAME() {
        return _NAME;
    }

    public void setNAME(String _NAME) {
        this._NAME = _NAME;
    }

    public Boolean getGENDER() {
        return _GENDER;
    }

    public void setGENDER(Boolean _GENDER) {
        this._GENDER = _GENDER;
    }

    public String getID() {
        return _ID;
    }

    public void setID(String _ID) {
        this._ID = _ID;
    }
}
