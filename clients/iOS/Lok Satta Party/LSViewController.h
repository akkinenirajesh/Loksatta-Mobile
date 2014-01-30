//
//  LSViewController.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LSNewsViewController.h"
#import "LSLeadersViewController.h"
#import "LSDonateViewController.h"
#import "LSVolunteerViewController.h"

@interface LSViewController : UIViewController<UITabBarControllerDelegate>
{
    UITabBarController *tabBarController;
}

@property(strong,nonatomic)UITabBarController *tabBarController;
@end
