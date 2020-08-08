import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SlidemenuModule } from './slidemenu/slidemenu.module';
import { TopbarModule } from './topbar/topbar.module';
import { ContentModule } from './content/content.module';
import { HomeComponent } from './home/home.component';
import { TabMenuModule } from 'primeng/tabmenu';
import { HttpClientModule } from '@angular/common/http';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { HttpService } from './common/http/http.service';
import { ToastService } from './common/message/message.service';
import { MessageService } from 'primeng/api';
import { WebSocketService } from './common/web-socket/web-socket.service'; 

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ContentModule,
    SlidemenuModule,
    TopbarModule,
    TabMenuModule,
    HttpClientModule,
    ToastModule,
    ButtonModule
  ],
  providers: [MessageService, ToastService, HttpService, WebSocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
