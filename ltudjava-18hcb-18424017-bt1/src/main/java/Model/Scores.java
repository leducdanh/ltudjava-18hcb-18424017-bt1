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
public class Scores {
    private String _IdStudent;
    private String _Name;
    private String _ScoreMidSemester;
    private String _ScoreEndSemester;
    private String _Scoreplus;
    private String _ScoreSummary;

    public Scores() {
    }

    public Scores(String _IdStudent, String _Name, String _ScoreMidSemester, String _ScoreEndSemester, String _Scoreplus, String _ScoreSummary) {
        this._IdStudent = _IdStudent;
        this._Name = _Name;
        this._ScoreMidSemester = _ScoreMidSemester;
        this._ScoreEndSemester = _ScoreEndSemester;
        this._Scoreplus = _Scoreplus;
        this._ScoreSummary = _ScoreSummary;
    }

    

    public String getIdStudent() {
        return _IdStudent;
    }

    public void setIdStudent(String _IdStudent) {
        this._IdStudent = _IdStudent;
    }

    public String getName() {
        return _Name;
    }

    public void setName(String _Name) {
        this._Name = _Name;
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
