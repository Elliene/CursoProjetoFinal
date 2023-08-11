package br.unb.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.unb.dominio.Aluno;
import br.unb.dominio.Disciplina;

public class AlunoDisciplinaAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AlunoDisciplinaForm alunoForm = (AlunoDisciplinaForm) form;

		// Simulação dos alunos e disciplinas (substitua pelos dados do banco de dados)
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(1, "João", "2021001"));
		alunos.add(new Aluno(2, "Maria", "2021002"));
		// Adicione mais alunos aqui

		List<Disciplina> disciplinas = new ArrayList<>();
		disciplinas.add(new Disciplina(1, "Matemática", "Turma A", "Engenharia", "local"));
		disciplinas.add(new Disciplina(2, "História", "Turma B", "História", "local"));
		// Adicione mais disciplinas aqui

		// Encontrar o aluno e disciplina selecionados
		Aluno alunoSelecionado = null;
		Disciplina disciplinaSelecionada = null;
		for (Aluno aluno : alunos) {
			if (aluno.getId() == alunoForm.getAlunoId()) {
				alunoSelecionado = aluno;
				break;
			}
		}

		for (Disciplina disciplina : disciplinas) {
			if (disciplina.getId() == alunoForm.getDisciplinaId()) {
				disciplinaSelecionada = disciplina;
				break;
			}
		}

		request.setAttribute("alunoList", alunos);
		request.setAttribute("disciplinaList", disciplinas);

		// Adicionar o aluno vinculado à disciplina ao request para exibir na página
		request.setAttribute("aluno", alunoSelecionado);
		request.setAttribute("disciplina", disciplinaSelecionada);
		if (alunoSelecionado != null) {
			return mapping.findForward("sucesso");
		}
		return mapping.findForward("input");
	}
}