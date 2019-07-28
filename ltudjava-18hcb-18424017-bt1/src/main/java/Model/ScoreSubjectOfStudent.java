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
    private String _ScoreMidSemester;
    private String _ScoreEndSemester;
    private String _Scoreplus;
    private String _ScoreSummary;

    public ScoreSubjectOfStudent() {
    }

    public ScoreSubjectOfStudent(String _IdSubject, String _NameSubject, String _ScoreMidSemester, String _ScoreEndSemester, String _Scoreplus, String _ScoreSummary) {
        this._IdSubject = _IdSubject;
        this._NameSubject = _NameSubject;
        this._ScoreMidSemester = _ScoreMidSemester;
        this._ScoreEndSemester = _ScoreEndSemester;
        this._Scoreplus = _Scoreplus;
        this._ScoreSummary = _ScoreSummary;
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

    public String getScoreMidSemester() {
        return _ScoreMidSemester;
    }

    public void setScoreMidSemester(String _ScoreMidSemester) {
        this._ScoreMidSemester = _ScoreMidSemester;
    }

    public String getScoreEndSemester() {
        return _ScoreEndSemester;
    }

    public void setScoreEndSemester(String _ScoreEndSemester) {
        this._ScoreEndSemester = _ScoreEndSemester;
    }

    public String getScoreplus() {
        return _Scoreplus;
    }

    public void setScoreplus(String _Scoreplus) {
        this._Scoreplus = _Scoreplus;
    }

    public String getScoreSummary() {
        return _ScoreSummary;
    }

    public void setScoreSummary(String _ScoreSummary) {
        this._ScoreSummary = _ScoreSummary;
    }

    
    
    
}
