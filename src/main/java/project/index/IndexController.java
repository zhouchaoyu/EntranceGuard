package project.index;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index() {
		redirect("/token.html");
	}
}
 