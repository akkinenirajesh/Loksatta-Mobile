//
//  SplashView.h
//  Lok Satta
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>

typedef enum
{
    SplashViewAnimationNone,
    SplashViewAnimationSideLeft,
    SplashViewAnimationFade
} SplashViewAnimation;

@interface SplashView : UIView
{
    UIImageView *splashImage;
    UIImage *image;
    NSTimeInterval delay;
    BOOL touchallowed;
    SplashViewAnimation animation;
    NSTimeInterval animationDelay;
    BOOL isFinishing;
}

@property (retain) UIImage *image;
@property NSTimeInterval delay;
@property BOOL touchAllowed;
@property SplashViewAnimation animation;
@property NSTimeInterval animationDelay;
@property BOOL isFinishing;

- (id) initWithImage:(UIImage *)screenImage;
-(void)startSplash;
-(void)dismissSplash;
-(void)dismissSplashFinish;

@end
