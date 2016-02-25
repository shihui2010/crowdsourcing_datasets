package dataSet;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataSet.DataSet;
import dataSet.DataSetList;
import dataSet.Filter;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

/**
 * Servlet implementation class getFileList
 */
public class getFileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFileList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		try{
			response.setContentType("json/text");
			PrintWriter out= response.getWriter();
			String[] dataType = request.getParameterValues("data-type");
			String[] goal = request.getParameterValues("goal");
			String keyword = request.getParameter("keyword");
			LinkedList<DataSet> resultList = getMatchDataset(goal, dataType, keyword);
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for(DataSet resultItem : resultList){
				
				JsonObjectBuilder datasetBuilder = Json.createObjectBuilder();
				datasetBuilder.add("id", resultItem.getId())
						.add("name", resultItem.getName())
						.add("link", resultItem.getLink())
						.add("type", resultItem.getType())
						.add("goal", resultItem.getGoal())
						.add("size", resultItem.getSize())
						.build();
				JsonObject dataset = datasetBuilder.build();
				
				arrayBuilder.add(dataset);
			}
			
			JsonObject jsonResult = Json.createObjectBuilder().add("datasets", arrayBuilder.build()).build();

			out.print(jsonResult);
			request.setAttribute("datasets", jsonResult);
			request.getRequestDispatcher("/searchPage.jsp").forward(request, response);
//		}
//		catch(Exception e){}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private LinkedList<DataSet> getMatchDataset(String[] goal, String[] type, String keyword) throws IOException{
		Filter f = new Filter(type, goal, keyword);
		try{
			DataSetList list = new DataSetList("/Users/Helen-shi/Documents/workspace/crowdsourcing.data.searchpage/WebContent/filelist.txt");
			return f.getMatchDataset(list.getList());
		}
		catch(Exception e){System.out.println(e);}
		return null;
	}

}
