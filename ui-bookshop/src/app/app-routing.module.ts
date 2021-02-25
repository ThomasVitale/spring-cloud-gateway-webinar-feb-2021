import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BookListComponent } from './book-list/book-list/book-list.component';
import { BookService } from './book.service';
import { LogoutComponent } from './logout/logout.component';
import { LoginService } from './login.service';

const routes: Routes = [
  { path: 'books', component: BookListComponent },
  { path: 'logout', component: LogoutComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
    BrowserModule,
    HttpClientModule
  ],
  exports: [RouterModule],
  providers: [BookService, LoginService]
})
export class AppRoutingModule { }
