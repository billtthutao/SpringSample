package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;

import tacos.Taco;

@Configuration
public class SpringDataRestConfiguration {

  @Bean
  public ResourceProcessor<Resources<Resource<Taco>>>
    tacoProcessor(EntityLinks links) {

    return new ResourceProcessor<Resources<Resource<Taco>>>() {
      @Override
      public Resources<Resource<Taco>> process(
                          Resources<Resource<Taco>> resource) {
        resource.add(
            links.linkFor(Taco.class)
                 .slash("recent")
                 .withRel("recents"));
        return resource;
      }
    };
  }
  
}

