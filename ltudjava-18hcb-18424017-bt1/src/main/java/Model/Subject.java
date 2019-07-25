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
public class Subject {
    protected String _ID;
    protected String _NAME;
    protected String _ROOM;

    public Subject() {
    }

    public Subject(String _ID, String _NAME, String _ROOM) {
        this._ID = _ID;
        this._NAME = _NAME;
        this._ROOM = _ROOM;
    }

    public String getID() {
        return _ID;
    }

    public void setID(String _ID) {
        this._ID = _ID;
    }

    public String getNAME() {
        return _NAME;
    }

    public void setNAME(String _NAME) {
        this._NAME = _NAME;
    }

    public String getROOM() {
        return _ROOM;
    }

    public void setROOM(String _ROOM) {
        this._ROOM = _ROOM;
    }
}
