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
    private float _ScoreMidSemester;
    private float _ScoreEndSemester;
    private float _Scoreplus;

    public Scores(String _IdStudent, String _Name, float _ScoreMidSemester, float _ScoreEndSemester, float _Scoreplus, float _ScoreSummary) {
        this._IdStudent = _IdStudent;
        this._Name = _Name;
        this._ScoreMidSemester = _ScoreMidSemester;
        this._ScoreEndSemester = _ScoreEndSemester;
        this._Scoreplus = _Scoreplus;
        this._ScoreSummary = _ScoreSummary;
    }

    public Scores() {
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

    public float getScoreMidSemester() {
        return _ScoreMidSemester;
    }

    public void setScoreMidSemester(float _ScoreMidSemester) {
        this._ScoreMidSemester = _ScoreMidSemester;
    }

    public float getScoreEndSemester() {
        return _ScoreEndSemester;
    }

    public void setScoreEndSemester(float _ScoreEndSemester) {
        this._ScoreEndSemester = _ScoreEndSemester;
    }

    public float getScoreplus() {
        return _Scoreplus;
    }

    public void setScoreplus(float _Scoreplus) {
        this._Scoreplus = _Scoreplus;
    }

    public float getScoreSummary() {
        return _ScoreSummary;
    }

    public void setScoreSummary(float _ScoreSummary) {
        this._ScoreSummary = _ScoreSummary;
    }
    private float _ScoreSummary;
}
