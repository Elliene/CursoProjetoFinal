package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.unb.dao.DisciplinaDAO;
import br.unb.dominio.Aluno;
import br.unb.dominio.Disciplina;

public class DisciplinaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("DisciplinaAction::execute");
		DisciplinaForm disciplinaForm = (DisciplinaForm) form;
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		// Realize a validação da disciplnaForm antes de salvar
		ActionMessages errors = new ActionErrors();
		if (disciplinaForm.getNome() == null || disciplinaForm.getNome().trim().isEmpty()) {
			errors.add("nome", new ActionMessage("error.nome.required"));
		}
		if (disciplinaForm.getLocal() == null || disciplinaForm.getLocal().trim().isEmpty()) {
			errors.add("local", new ActionMessage("error.local.required"));
		}
		if (disciplinaForm.getTurma() == null || disciplinaForm.getTurma().trim().isEmpty()) {
			errors.add("turma", new ActionMessage("error.turma.required"));
		}
		if (disciplinaForm.getCurso() == null || disciplinaForm.getCurso().trim().isEmpty()) {
			errors.add("matricula", new ActionMessage("error.curso.required"));
		}

		if (!errors.isEmpty()) {
			// Se houver erros, adicione-os ao request e retorne a pÃ¡gina de entrada (input)
			saveErrors(request, errors);
//            return mapping.findForward("success");
		}
		String method = request.getParameter("method");
		System.out.println("DisciplinaAction::execute::method " + method);
		if (method != null && !method.isEmpty()) {
			switch (method) {
			case "salvar":
				Disciplina disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getLocal(), disciplinaForm.getTurma(),disciplinaForm.getCurso());
				disciplinaDAO.salvar(disciplina);
				break;
			case "editar":
				int id = Integer.parseInt(request.getParameter("id"));
				disciplina = disciplinaDAO.buscarPorId(id);
				disciplinaForm.setId(disciplina.getId());
				disciplinaForm.setNome(disciplina.getNome());
				disciplinaForm.setLocal(disciplina.getLocal());
				disciplinaForm.setTurma(disciplina.getTurma());
				disciplinaForm.setCurso(disciplina.getCurso());
				break;
			case "atualizar":
				disciplina = new Disciplina(disciplinaForm.getId(), disciplinaForm.getNome(), disciplinaForm.getLocal(), disciplinaForm.getTurma(),disciplinaForm.getCurso());
				disciplinaDAO.atualizar(disciplina);
				break;
			case "excluir":
				id = Integer.parseInt(request.getParameter("id"));
				disciplinaDAO.excluir(id);
				break;
			}
		}
			List<Disciplina> disciplinas = disciplinaDAO.listarTodos();
			request.setAttribute("disciplinas", disciplinas);

			return mapping.findForward("success");
		}
	}



	

