package group.maven_mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {
    @Parameter(property = "hello.name", defaultValue = "World")
    private String name;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello, " + name + "!");
    }
}
