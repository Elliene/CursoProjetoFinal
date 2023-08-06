package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.unb.dao.AlunoDAO;
import br.unb.dominio.Aluno;

public class AlunoAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("AlunoAction::execute");
    	AlunoForm alunoForm = (AlunoForm) form;
        AlunoDAO alunoDAO = new AlunoDAO();
        String method = request.getParameter("method");
        System.out.println("AlunoAction::execute::method "+ method);
        if (method != null && !method.isEmpty()) {
            switch (method) {
                case "salvar":
                    Aluno aluno = new Aluno(alunoForm.getNome(), alunoForm.getMatricula());
                    alunoDAO.salvar(aluno);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    aluno = alunoDAO.getById(id);
                    alunoForm.setId(aluno.getId());
                    alunoForm.setNome(aluno.getNome());
                    alunoForm.setMatricula(aluno.getMatricula());
                    break;
                case "atualizar":
                    aluno = new Aluno(alunoForm.getNome(), alunoForm.getMatricula());
                    alunoDAO.update(aluno);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    alunoDAO.delete(id);
                    break;
            }
        }

        List<Aluno> alunos = alunoDAO.findAll();
        request.setAttribute("alunos", alunos);

        return mapping.findForward("success");
    }
}
