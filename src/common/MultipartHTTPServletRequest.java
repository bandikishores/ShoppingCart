package common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;
 
/**
 * The mojarra implementation does not parse POST request that are multipart encoded, 
 * because the parameters are not accessable via <code>getParameter()</code>.
 *
 * This class extends the HttpServletRequest to provide access to the parameters
 * which are encoded accessable via <code>getParts</code>.
 *
 * All parts are made visible that have <code>contentType == null && size < 300</code>.
 *
 * If the request is not multipart encoded, the wrapper doesn't modify the behavior of the original <code>HttpServletRequest</code>.
 * @author Kishore
 */
public class MultipartHTTPServletRequest extends HttpServletRequestWrapper {
 
    protected Map<String, String> parameterParts = new HashMap<String, String>();
 
    public MultipartHTTPServletRequest(HttpServletRequest request) {
        super(request);
 
        if (getContentType() == null) {
            return;
        }
        if (!getContentType().toLowerCase().startsWith("multipart/")) {
            return;
        }
        try {
            for (Part i : getParts()) {
                if (i.getContentType() == null && i.getSize() < 300) {
                    parameterParts.put(i.getName(), getData(i.getInputStream()));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MultipartHTTPServletRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(MultipartHTTPServletRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private static String getData(InputStream input) throws IOException {
        String data = "";
        String line = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while ((line = reader.readLine()) != null) {
            data += line;
        }
        return data;
    }
 
    @Override
    public String getParameter(String name) {
        String result = super.getParameter(name);
        if (result == null) {
            return parameterParts.get(name);
        }
        return result;
    }
}