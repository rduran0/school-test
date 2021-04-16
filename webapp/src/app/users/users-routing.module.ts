import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './pages/add/add.component';
import { EditComponent } from './pages/edit/edit.component';
import { ViewComponent } from './pages/view/view.component';

const routes: Routes = [
  {path: '',
    children: [
      {path: 'add', component: AddComponent},
      {path: 'edit', component: EditComponent},
      {path: 'view', component: ViewComponent},
      {
        path: '**',
        redirectTo: 'view'
      }
    ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
