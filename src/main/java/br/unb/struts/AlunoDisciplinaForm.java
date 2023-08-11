package br.unb.struts;

import org.apache.struts.action.ActionForm;

public class AlunoDisciplinaForm extends ActionForm {
    private int alunoId;
    private int disciplinaId;

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}