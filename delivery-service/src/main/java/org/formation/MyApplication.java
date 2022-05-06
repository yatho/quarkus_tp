package org.formation;

import io.quarkus.arc.profile.UnlessBuildProfile;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
@UnlessBuildProfile("test")
public class MyApplication extends Application {
}
