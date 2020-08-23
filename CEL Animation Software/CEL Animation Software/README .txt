==================RUNNING AN ANIMATION=====================

To start an animation the following must be done:
1. Create a text file that contains canvas dimension specs, shape declarations, and motion
2. Ensure that the text file is in the same directory as the src
3. Pass in the arguments, '-in' followed by the file that describes the animation, '-view' followed
   by the type of view that you would like outputted, (optional) '-out' followed by the file you
   would like the output to be written to (doesn't apply for visual views), (optional) '-speed'
   followed by the speed at which you would like the view to run at (doesn't apply to the text view)

========================VIEW===============================

A MovieView is an interface for viewing a MovieModel.

It is implemented by the classes TextView, VisualView, and SVGView, representing the three ways an
animation file can be read and output.

We created a factory class that creates the appropriate MovieView depending on the command line
String argument following the '-view' specifier.
EDIT-------------------------------------------------------
We added an EditorView class that extends the visual view to add additional functionality
that the editor view can use in order to encapsulate the editor view.

----------------------- EDITOR VIEW -----------------------

The editor view enables the user to play, pause, restart, and loop an animation. At any given time
the user may insert/edit/delete keyframes as they please and see the results immediately. The user can
also set the speed of the animation and add supported shapes.

We decided to have the animation play in one frame while the editor fields and buttons are in their
own frame. This way the buttons (since there are many) don't get in the way of the animation.

In order to insert/edit a key frame the user must select an existing shape from the drop down menu
and the user must fill in all shape attribute fields before clicking the 'edit keyframe' button. To
delete a key frame the user must enter a tick that references an existing key frame and click the
'delete keyframe' button.

If the user wants to add a shape they must fill in the shape attribute fields, additionally filling
out the 'name' field at the bottom. Then the user must click either the 'add rectangle' or
'add ellipse' button to create the shape.

========================MODEL==============================
------------------ OVERALL FUNCTIONALITY ------------------

A Movie represents an animated movie with all of its shapes to be animated.

We store 'how the shape moves' in a ShapeAnimation which contains a list of its Transformations. 
With the Transformations we can retrieve what a given shape will look like at a certain frame.

------------------- DETAILS & INVARIANTS ------------------

An animation is modeled by a MovieModel.

A MovieModelImpl implements MovieModel,
and represents a movie with values for the movie's runtime, background color,
and all the ShapeAnimations in the movie. The runtime can be set to be the end of the last
ShapeAnimation, or a custom positive length.
EDIT-------------------------------------------------------
The core functions of a MovieModel such as adding shapes to a movie and transformations for 
shapes in the movie are now located in a new interface MovieModelCore.
MovieModel now extends MovieModelCore. MovieModel has additional functionalities for getting
and setting methods. This was done for organizational purposes.

A ShapeAnimation represents a shape with a series of motions called Transformations to be
applied to it. It contains a Shape and a List of Transformations.

A Transformation is a motion to be applied to a Shape in a movie. There are 3 different
transformations: Resize, Translate, and ColorChange. All transformations must have a non-negative
start and end tick, and the end tick must be greater than the start tick. They have an apply
method which applies the Transformation to the given shape to show how the shape would appear
at that time.
EDIT-------------------------------------------------------
Transformations must now take place one after another, meaning there cannot be gaps in time between
two animations of the same type.

The apply method works by finding the change between the start and end values for the
Transformation, dividing them by the duration of the Transformation, then multiplying by the given
tick and adding to the start value. The apply method is only called on tick values in the range
of the start and end tick.

A ColorChange must be given a valid Color for the start and end colors, and a
Resize must be given positive numbers for the start and end dimensions.

A Shape represents a shape with an name, Posn position, width, height, and RGB Color. A shape must
have a name. A shape cannot have a negative width and/or height.
EDIT-------------------------------------------------------
String and void accept methods were added to take in a visitor objects added for the purpose of
generating the views.

A position Posn is allowed to be negative, it just means it is to the left/right of the origin.

A Color must be in range [0,255].

A Shape and its Transformations can be added to a MovieModel using addShape, addTranslationToShape,
addResizeToShape, and addColorChangeToShape. You cannot add transformations if the given shape
name does not exist or the given transformation overlaps with a transformation of the same type,
has mismatching start values with the end values of the transformation of the same type just before
it, or has mismatching end values with the start values of the transformation of the same type just
after it.
A shape can be deleted using deleteShape, which deletes the shape with the given name and all of
its Transformations, or a specific Translate, ColorChange, or Resize can be deleted by giving the
start tick of specific transformation to be deleted from the shape with the given name. You cannot
delete a shape with a name that does not exist in the movie, or a transformation that does not exist
at the tick for the specified shape.

A MovieModel can get a Frame of the movie at a given time. A Frame contains a list of shapes at
their positions on the canvas at a given time.

Key frames can be freely edited by the user. A user can add a key frame anywhere in an animation so
long as the tick is greater than 0, if the key frame already exists then it is replaced with the
given key frame. Key frames can also be deleted (given a tick), the animation will be 'tweened'
accordingly so that there are no gaps in shape motions.

==================EXTRA CREDIT CHANGES=====================

We did all 3 tiers of the extra credit.

We added a rotate transformation that allows users to optionally make shapes rotate. We had to add a
few functions to enable this.

Added a new model called MovieModelLayerImpl that implements layer functionality: the user can add
and delete shapes on several layers that determine which shapes appear 'above' or 'below' each other
when overlapping.

We used the 'dumber' MovieModelImpl to represent one layer of an animation. Each has a set of
shapeAnimations to be displayed, and each represents a layer with a unique name.

We edited the EditorView to include objects that represent the scrubber for the movie, the buttons
to mutate the layers, and a button to export to an SVG file directly. The EditorDisplayView is now
private and only accessible to the EditorView.

We tested everything.

