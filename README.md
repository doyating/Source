#Gearbox Sublime Sencha Congratulations! You're about to supercharge your Ext JS development. The Gearbox Sublime Sencha plugin aids development with snippets, shortcuts and a little bit of magic.

Prerequisites
Installation
Related Files
Commands
SnippetsExt JS specific snippets
Gearbox snippets
Convenience snippets
Testing snippets
ConfigurationProperty Templates
Function Template
AdvancedAuto Update
application Paths
jsduck Paths
jsduck buildpaths
jsduck args
Related Files PatternsWhere to look in general
Where to look for related files.
##Prerequisites In order to use this package to its full extent, you should first install:

ruby 2.0+
Sencha CMD
JsDuck
Sublime Text
##Installation The easiest way to install this package is through sublime package control, we reccommend you install it, if you don't already have it. Then from your Sublime Text editor, open the command prompt (default: Ctrl + ⇧ + P or ⌘ + ⇧ + P) and type "package control install package". Press enter to start the package control (may take a couple of seconds to load), then search for "GearboxSencha".

The only installation steps to take after installing this sublime package are configuring the package and tell JsDuck to build the docs for the first time. Once configured, run the Rebuild jsDuck command from a file in your project and you're all set!

##Related Files The related files plugin helps you quickly switch between files that are strongly related, like the Model, View and Controller of the same type.

##Commands The listed keycodes are the defaults. You can change them in the configuration.

Related Files

command: related_files	
Linux shortcut: alt+shift+p	
Mac OSX shortcut: alt+shift+p	

Related Files Lucky

command: related_files_lucky	
Linux shortcut: ctrl+shift+alt+p	
Mac OSX shortcut: ctrl+shift+⌘+p	

Class related classes

command: class_related_classes	
Linux shortcut: super+alt+p	
Mac OSX shortcut: ⌘+alt+p	

Class functions

command: class_functions	
Linux shortcut: super+alt+r	
Mac OSX shortcut: ⌘+alt+r	

Class properties

command: class_properties	
Linux shortcut: super+alt+ctrl+r	
Mac OSX shortcut: ⌘+alt+y	

Restart Sublime (dev only)

command: restart_sublime	
Linux shortcut: f5	
Mac OSX shortcut: f5	

Rebuild jsDuck

command: rebuild_jsduck	
Linux shortcut: super+alt+j	
Mac OSX shortcut: ⌘+alt+j	

SnippetsSnippets speed up your development by shortening the code you write over and over again. Like a class definition. The Gearbox Sublime Sencha Gear offers four kinds of snippets:

Ext JS specific snippets
Definition snippets
Convenience snippets
Testing snippets
###Ext JS specific snippets

afterRender

tabTrigger: afterRender	
selection:	Added as the function body	
Adds an afterRender method to your class, with a call to the parent.

afterRender: function() {
	this.debug(arguments);
	
	this.callParent(arguments);
},

callParent

tabTrigger: callParent	
Adds a call to the parent with arguments.

return this.callParent(arguments);

constructor

tabTrigger: constructor	
Adds a constructor with ApplyIf and a call to the parent.

constructor: function(config) {
	this.debug(arguments);

	Ext.applyIf(config || {}, {
		
	});

	return this.callParent(arguments);
},

create

tabTrigger: create	
after:	Ext.Component, config	
Creates an Ext.create statement.

Ext.create('', {
	
});

define

tabTrigger: define	
after:	name, parent, xtype, body	
selection:	Added as body	
Creates an Ext.define statement.

Ext.define('', {
	extend: 'Ext.',
	xtype: '',

	//

	mixins: [
		'Gearbox.mixin.ModelInfo',
		'Gearbox.mixin.Logger'
	],

	logLevel: 'debug',

	//

	
});e

requires

tabTrigger: requires	
Adds an empty requires array.

requires: [
	''
],

initComponent

tabTrigger: initComponent	
Inserts Ext JS' initComponent function, with a call to the parent.

initComponent: function() {
	this.debug(arguments);
	
	this.callParent(arguments);
},

items

tabTrigger: items	
Inserts an array of objects.

items: [{
	
}],

mixins

tabTrigger: mixins	
Adds a mixins block with some default Gearbox mixins.

mixins: [
	'Gearbox.mixin.ModelInfo',
	'Gearbox.mixin.Logger'
],

logLevel: 'debug',

//

###Definition snippets

tabTrigger: f	
Adds a new anonymous function.

function() {
	
}

fn

tabTrigger: fn	
Inserts a new anonymous method.

: function() {
	this.debug(arguments);

	
},

function

tabTrigger: function	
Adds a new anonymous function with a debug line.

function() {
	this.debug(arguments);

	
},

###Gearbox snippets log

tabTrigger: log	
selection:	As argument to the logger. 
Adds a call to a local logger: this.log.

this.log(arguments);

log variable

tabTrigger: logv	
Add a log showing the value of a variable.

this.log('', '=', );

log annotated

tabTrigger: loga	
Same as logv, but with an annotation.

this.log(':', ' =', );

###Convenience snippets console.log variable : tabTrigger: clogv	
Add a console.log statement to output a variable and its value.

console.log('', '=', );

debug

tabTrigger: debug	
after:	The object to debug	
Adds a call to this.debug with arguments as the default argument.

this.debug(arguments);

each

tabTrigger: each	
Adds a lodash each block.

_.each(arr, function(item, key) {
	console.log('item:', key, '=', item);
	
});

eachPush

tabTrigger: eachPush	
Same as each, but push each item to an array.

var items = [];
_.each(arr, function(item, key) {
	console.log('arr:', key, '=', item);
	
	items.push(item);
});

line

tabTrigger: dashes	
Adds a line of dashes.

////////////////////////////////////////////////////////////////////////////

map

tabTrigger: map	
Adds a lodash map block.

var items = _.map(arr, function(item, key) {
	this.log('arr:', key, '=', item);

	return item;
}, this);

next

tabTrigger: next	
Create a new function with a next function as argument.

function(next) {
	
	next();
}

promise

tabTrigger: Promise	
Add a return Promise block.

return new Promise(function(resolve, reject) {
	
});

###Testing snippets describe

tabTrigger: describe	
Insert t.describe block.

t.describe('', function(t) {
	
});

it

tabTrigger: it	
Insert t.it block.

t.it('should ', function(t) {
	
});

requireOK

tabTrigger: requireOK	
Insert a requireOK block.

t.requireOk([
	''
]);

startTest

tabTrigger: startTest	
Insert a startTest block.

startTest(function(t) {
	
});

##Configuration

All configuration options explained.

propertyTemplatesSnippet templates used to insert properties into the file < name > gets replaced with the property name. This format uses 1 array entry per line, and supports Sublime Snippet features.

"String": [
	"<name>: '$1'"
],
"Boolean": [
	"<name>: ${1:false}"
],
"Object": [
	"<name>: {",
	"	'$1': '$1'",
	"}"
],
"Array": [
	"<name>: [",
	"	${1:''}",
	"]"
],
"Number": [
	"<name>: ${1:0}"
]

"functionTemplate"Snippet templates used to insert functions into the file < name > gets replaced with the property name. This format uses 1 array entry per line, and supports Sublime Snippet features.

"<name>: function(config) {",
	"	this.callParent(arguments);",
	"	$1",
"}"

###Advanced These settings are only for advanced users. For a normal project, you won't need to edit any of this.

"autoUpdate"This is an experimental feature still being worked on, it will try to update class information in the background.

"enabled": false,
"interval": 300

"applicationPaths"Paths the plugin will look for a valid application folder, relative to working directory root.

[
	"/desktop",
	"/"
]

"jsduckPaths"Paths where the plugin will look for valid jsduck formatted data to use to provide related functions/properties/classes, relative to application folder.

[
	"jsduck",
	"docs"
]

"jsduckbuildpaths"Folders to include when building jsduck information, relative to application folder.

[
	"app",
	"ext/src"
]

jsduckargsOnly edit when you really know how jsduck and this plugin works.

####relatedFilesPatterns This is done using a regexes, but don't despair; you don't need to configure anything.

#####Where to look in general The file contains a big commented regex and a couple of smaller regexes, the big one tells the plugin where to look for your js files, the smaller ones tell it where to look for related files.

Here's the big one:

^.+?\/(?:app|src|tests)(?:\/\\w*)?(.*\/?)?\/(\\w*)(?:\\.t)?\\.js$

Scary, huh? Fear not, for a normal project, you'll need not edit anything. This regex describes the general structure of your project.

#####Where to look for related files. Search for any files matching the following structure($1=currentModuleName, $2=currentFileName):

"*/model/$2.js",
"*/model/*/$2.js",
"*/model/*/*/$2.js",

"*/$1/$2.*js",
"*/*/$1/$2.*js",
"*/*/*/$1/$2.*js",

"*/packages/gearbox/*/$1/$2.*js",
"*/packages/gearbox/*/*/$1/$2.*js",

"*/ext/src/$1/$2.js"
