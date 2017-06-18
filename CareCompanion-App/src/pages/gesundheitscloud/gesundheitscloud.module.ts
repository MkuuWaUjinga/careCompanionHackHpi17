import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { GesundheitscloudPage } from './gesundheitscloud';

@NgModule({
  declarations: [
    GesundheitscloudPage,
  ],
  imports: [
    IonicPageModule.forChild(GesundheitscloudPage),
  ],
  exports: [
    GesundheitscloudPage
  ]
})
export class GesundheitscloudPageModule {}
