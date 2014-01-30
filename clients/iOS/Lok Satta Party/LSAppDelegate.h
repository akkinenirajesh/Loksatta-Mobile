//
//  LSAppDelegate.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SplashView.h"
#import "LSViewController.h"
@class LSMainViewController;
@interface LSAppDelegate : UIResponder <UIApplicationDelegate>{
    UIWindow *window;
    SplashView *mySplash;
    UINavigationController *navController;
}

@property (strong, nonatomic) UIWindow *window;
@property (strong,nonatomic)LSViewController *mainViewController;
@property(strong,nonatomic)UINavigationController *navController;

@end
