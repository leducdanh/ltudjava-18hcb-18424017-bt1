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
public class ScoreSubjectOfStudent {
    private String _IdSubject;
    private String _NameSubject;
    private String _Score;

    public ScoreSubjectOfStudent() {
    }

    
    public ScoreSubjectOfStudent(String _IdSubject, String _NameSubject, String _Score) {
        this._IdSubject = _IdSubject;
        this._NameSubject = _NameSubject;
        this._Score = _Score;
    }

    
    public String getIdSubject() {
        return _IdSubject;
    }

    public void setIdSubject(String _IdSubject) {
        this._IdSubject = _IdSubject;
    }

    public String getNameSubject() {
        return _NameSubject;
    }

    public void setNameSubject(String _NameSubject) {
        this._NameSubject = _NameSubject;
    }

    public String getScore() {
        return _Score;
    }

    public void setScore(String _Score) {
        this._Score = _Score;
    }
    
    
}
