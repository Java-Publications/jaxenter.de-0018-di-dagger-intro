/*
 * Copyright [2014] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.demo.jaxenter.blog0018.step01.business;

import dagger.Module;
import dagger.Provides;
import org.rapidpm.demo.jaxenter.blog0018.step01.Main;

import org.rapidpm.demo.jaxenter.blog0018.Context;
import org.rapidpm.demo.jaxenter.blog0018.business.MainService;
import org.rapidpm.demo.jaxenter.blog0018.business.MainServiceImpl;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.SubService;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.impl_a.SubServiceA;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.impl_b.SubServiceB;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.subsubservice.SubSubService;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.subsubservice.SubSubServiceImpl;

import javax.inject.Named;

/**
 * Created by Sven Ruppert on 17.11.2014.
 */

@Module(library = false,
    injects = {Main.class},
    complete = true)
public class BusinessModule {

  @Provides
  MainService provideMainService(MainServiceImpl mainService) {
    return mainService;
  }

  @Provides @Named("A")
  SubService provideSubServiceA(SubServiceA subService) {
    return subService;
  }
  @Provides @Named("B")
  SubService provideSubServiceB(SubServiceB subService) {
    return subService;
  }

  @Provides //da kein Inject in Impl... new verwenden
  SubSubService provideSubSubService() {
    return new SubSubServiceImpl();
  }

  @Provides
  SubService provideSubService(@Named("A") SubService subServiceA, @Named("B") SubService subServiceB) {
    if(Context.getInstance().defaultImpl){
      return subServiceA;
    } else{
      return subServiceB;
    }
  }
}
