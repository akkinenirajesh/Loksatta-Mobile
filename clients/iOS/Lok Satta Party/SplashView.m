//
//  SplashView.m
//  Lok Satta
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import "SplashView.h"

@implementation SplashView
@synthesize image;
@synthesize delay;
@synthesize touchAllowed;
@synthesize animation;
@synthesize animationDelay;
@synthesize isFinishing;

-(id)initWithImage:(UIImage *)screenImage
{
    if(self==[super initWithFrame:CGRectMake(0, 0, 1024, 768)]){
        self.image = screenImage;
        self.delay=4;
        self.touchAllowed=YES;
        self.animation=SplashViewAnimationNone;
        self.animationDelay=1;
        self.isFinishing=NO;
    }
    return self;
}

-(void)startSplash{
    splashImage=[[UIImageView alloc]initWithImage:self.image];
    [self addSubview:splashImage];
    [self performSelector:@selector(dismissSplash) withObject:self afterDelay:self.delay];
}

-(void)dismissSplash
{
    if (self.isFinishing||self.animation==SplashViewAnimationNone) {
        [self dismissSplashFinish];
    } else if(self.animation==SplashViewAnimationSideLeft) {
        CABasicAnimation *animSplash = [CABasicAnimation animationWithKeyPath:@"transform"];
        animSplash.duration=self.animationDelay;
        animSplash.removedOnCompletion = NO;
        animSplash.fillMode=kCAFillModeForwards;
        animSplash.toValue=[NSValue valueWithCATransform3D:CATransform3DMakeAffineTransform(CGAffineTransformMakeTranslation(-1024, 0))];
        animSplash.delegate=self;
        [self.layer addAnimation:animSplash forKey:@"animateTransform"];
    }else if (self.animation==SplashViewAnimationFade){
        CABasicAnimation *animSplash = [CABasicAnimation animationWithKeyPath:@"opacity"];
        animSplash.duration=self.animationDelay;
        animSplash.removedOnCompletion = NO;
        animSplash.fillMode=kCAFillModeForwards;
        animSplash.toValue=[NSNumber numberWithFloat:0];
        animSplash.delegate=self;
        [self.layer addAnimation:animSplash forKey:@"animateOpacity"];
    }
    self.isFinishing=YES;
}

-(void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag
{
    [self dismissSplashFinish];
}

-(void)dismissSplashFinish
{
    if(splashImage){
        [splashImage removeFromSuperview];
        [self removeFromSuperview];
    }
}

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    if (self.touchAllowed) {
        [self dismissSplash];
    }
}

-(BOOL) shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    if(interfaceOrientation==UIInterfaceOrientationLandscapeLeft){
        return interfaceOrientation==UIInterfaceOrientationLandscapeLeft;
    }
    return interfaceOrientation==UIInterfaceOrientationLandscapeRight;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
