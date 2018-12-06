
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import java.util.logging.Logger;


@WebServlet(name = "AdditionerServlet", urlPatterns = { "/add" })


public class AdditionerServlet extends HttpServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final static Logger LOGGER = Logger.getLogger(AdditionerServlet.class.getName());
		private Integer defaut = null;


		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

			try {
				
				int param1 = Integer.parseInt(req.getParameter("param1"));
				int param2 = Integer.parseInt(req.getParameter("param2"));
				int somme = param1 + param2;
				resp.setContentType("text/plain");
				resp.setStatus(200);
				resp.getWriter().println(somme); // addition des deux nombres
				LOGGER.info("Le premier paramètre que l'utilisateur a renseigné est : " + param1 +  " .");
				LOGGER.info("Le second paramètre que l'utilisateur a renseigné est : " + param2 +  " .");
				LOGGER.info("La somme des deux paramètres est : " + somme  + "  .");




			} catch (NumberFormatException e) {
				
				try {

					
					int param1 = Integer.parseInt(req.getParameter("param1"));
					if (this.defaut != null) {
						int param2 = this.defaut;
						int result = param1 + param2;
						LOGGER.fine("Le paramètre par défaut est" + this.defaut);
						resp.setStatus(200);
						resp.getWriter().println(result);
					} else {
						LOGGER.warning("Le paramètre 2 n'est pas renseigné.");
						throw new NumberFormatException("NumberFormatException thrown");
					}

				} catch (NumberFormatException e2) {
					LOGGER.warning("Il y a des valeurs manquantes ou erronnées");
					resp.setStatus(400);
					resp.getWriter().println("Exécution impossible, paramètre manquant.");
				}
			}
		}
		
		@Override
		protected void doPut(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
			resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
			resp.setContentType(MediaType.TEXT_PLAIN);
		
			resp.setContentType("text/plain");
			try {
				
				defaut = Integer.parseInt(req.getParameter("param2"));
				resp.getWriter().println("ok");
				LOGGER.warning("Vous avez envoyé le paramètre + " + defaut + " et ce dernier sera pris comme paramètre par défaut .");

				
			}catch(NumberFormatException e){
				resp.setStatus(400);
				
				resp.getWriter().println("Exécution impossible, paramètre manquant.");
			}
		}
}