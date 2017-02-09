package ch.andreas.thesis.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig.Host;
import org.eclipse.jgit.util.FS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by txh9o on 09.02.2017.
 */
public class CustomSshSessionFactory extends JschConfigSessionFactory {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected URL identityKeyURL;
    protected URL knownHosts;

    public CustomSshSessionFactory(URL identityKeyURL, URL knownHosts) {
        this.identityKeyURL = identityKeyURL;
        this.knownHosts = knownHosts;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jgit.transport.JschConfigSessionFactory#configure(org.eclipse.jgit.transport.OpenSshConfig.Host, com.jcraft.jsch.Session)
     */
    @Override
    protected void configure(Host hc, Session session) {
        // nothing special needed here.
    }

    /* (non-Javadoc)
     * @see org.eclipse.jgit.transport.JschConfigSessionFactory#getJSch(org.eclipse.jgit.transport.OpenSshConfig.Host, org.eclipse.jgit.util.FS)
     */
    @Override
    protected JSch getJSch(Host hc, FS fs) throws JSchException {
        JSch jsch = super.getJSch(hc, fs);
        // Clean out anything 'default' - any encrypted keys
        // that are loaded by default before this will break.
        jsch.removeAllIdentity();
        int count = 0;

        setIdentityKey(jsch, count);

        setKnownHosts(jsch);

        return jsch;
    }

    private void setKnownHosts(JSch jsch) throws JSchException {
        try (InputStream stream = knownHosts.openStream()) {
            jsch.setKnownHosts(stream);
        } catch (IOException e) {
            log.error("Failed to load known hosts " + knownHosts.getPath());
        }
    }

    private void setIdentityKey(JSch jsch, int count) throws JSchException {
        try (InputStream stream = identityKeyURL.openStream()) {
            jsch.addIdentity("key" + ++count, StreamUtils.copyToByteArray(stream), null, null);
        } catch (IOException e) {
            log.error("Failed to load identity " + identityKeyURL.getPath());
        }
    }
}
