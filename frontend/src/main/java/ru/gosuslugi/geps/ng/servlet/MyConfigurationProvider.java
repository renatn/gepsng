package ru.gosuslugi.geps.ng.servlet;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

/**
 * User: renatn
 * Date: 23.01.13
 * Time: 12:25
 */
public class MyConfigurationProvider extends HttpConfigurationProvider {
    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/").to("/index.jsp"))
                .addRule(Join.path("/signin").to("/signin.jsp"));
    }

    @Override
    public int priority() {
        return 10;
    }
}
