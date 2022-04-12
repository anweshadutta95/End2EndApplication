package com.myproject.microservice.apigateway.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
		Function<PredicateSpec, Buildable<Route>> routeFunction 
			= p -> p.path("/get")	//any request with path /get
					.filters(f -> f.addRequestHeader("MyHeader", "MyURI")) //will be first added a header
					.uri("http://httpbin.org:80"); //and then routed to this uri
			
		Function<PredicateSpec, Buildable<Route>> collegeRouteFunction 
			= p -> p.path("/colleges/**")	//any request starting with path /colleges/
					.uri("lb://college-service"); //and then routed to this service name registered in naming server;
			
		Function<PredicateSpec, Buildable<Route>> deptRouteFunction 
			= p -> p.path("/departments/**")	//any request starting with path /departments/
					.uri("lb://department-service"); //and then routed to this service name registered in naming server;
			
		Function<PredicateSpec, Buildable<Route>> facRouteFunction 
			= p -> p.path("/faculties/**")	//any request starting with path /faculties/
					.uri("lb://faculty-service"); //and then routed to this service name registered in naming server;
			
		Function<PredicateSpec, Buildable<Route>> collegeRewriteRouteFunction 
			= p -> p.path("/colleges-new/**")	//any request starting with path /colleges-new/
					.filters(f -> f.rewritePath(
									"/colleges-new/(?<segment>.*)", 
									"/colleges/${segment}"))
					.uri("lb://college-service"); //and then routed to this service name registered in naming server;
			
		return routeLocatorBuilder.routes()
				.route(routeFunction)
				.route(collegeRouteFunction)
				.route(deptRouteFunction)
				.route(facRouteFunction)
				.route(collegeRewriteRouteFunction)
				.build();
	}
	
}
