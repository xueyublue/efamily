package sg.xueyu.zebra.action;

import javax.servlet.http.Part;

public interface Uploadable {

	public void setFilenames(String[] filenames);

	public void setParts(Part[] parts);

}
