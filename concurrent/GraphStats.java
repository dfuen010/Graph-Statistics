package concurrent;

// Import declarations generated by the SALSA compiler, do not modify.
import java.io.IOException;
import java.util.Vector;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import salsa.language.Actor;
import salsa.language.ActorReference;
import salsa.language.Message;
import salsa.language.RunTime;
import salsa.language.ServiceFactory;
import gc.WeakReference;
import salsa.language.Token;
import salsa.language.exceptions.*;
import salsa.language.exceptions.CurrentContinuationException;

import salsa.language.UniversalActor;

import salsa.naming.UAN;
import salsa.naming.UAL;
import salsa.naming.MalformedUALException;
import salsa.naming.MalformedUANException;

import salsa.resources.SystemService;

import salsa.resources.ActorService;

// End SALSA compiler generated import delcarations.

import java.util.*;
import java.io.*;
import concurrent.Graph;
import concurrent.FileParser;

public class GraphStats extends UniversalActor  {
	public static void main(String args[]) {
		UAN uan = null;
		UAL ual = null;
		if (System.getProperty("uan") != null) {
			uan = new UAN( System.getProperty("uan") );
			ServiceFactory.getTheater();
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("ual") != null) {
			ual = new UAL( System.getProperty("ual") );

			if (uan == null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an actor to have a ual at runtime without a uan.");
				System.err.println("	To give an actor a specific ual at runtime, use the identifier system property.");
				System.exit(0);
			}
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("identifier") != null) {
			if (ual != null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an identifier and a ual with system properties when creating an actor.");
				System.exit(0);
			}
			ual = new UAL( ServiceFactory.getTheater().getLocation() + System.getProperty("identifier"));
		}
		RunTime.receivedMessage();
		GraphStats instance = (GraphStats)new GraphStats(uan, ual,null).construct();
		gc.WeakReference instanceRef=new gc.WeakReference(uan,ual);
		{
			Object[] _arguments = { args };

			//preAct() for local actor creation
			//act() for remote actor creation
			if (ual != null && !ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {instance.send( new Message(instanceRef, instanceRef, "act", _arguments, false) );}
			else {instance.send( new Message(instanceRef, instanceRef, "preAct", _arguments, false) );}
		}
		RunTime.finishedProcessingMessage();
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new GraphStats(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return GraphStats.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new GraphStats(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return GraphStats.getReferenceByLocation(new UAL(ual)); }
	public GraphStats(boolean o, UAN __uan)	{ super(false,__uan); }
	public GraphStats(boolean o, UAL __ual)	{ super(false,__ual); }
	public GraphStats(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null, sourceActor); }
	public GraphStats(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual, sourceActor); }
	public GraphStats(UniversalActor.State sourceActor)		{ this(null, null, sourceActor);  }
	public GraphStats()		{  }
	public GraphStats(UAN __uan, UAL __ual, Object obj) {
		//decide the type of sourceActor
		//if obj is null, the actor must be the startup actor.
		//if obj is an actorReference, this actor is created by a remote actor

		if (obj instanceof UniversalActor.State || obj==null) {
			  UniversalActor.State sourceActor;
			  if (obj!=null) { sourceActor=(UniversalActor.State) obj;}
			  else {sourceActor=null;}

			  //remote creation message sent to a remote system service.
			  if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {
			    WeakReference sourceRef;
			    if (sourceActor!=null && sourceActor.getUAL() != null) {sourceRef = new WeakReference(sourceActor.getUAN(),sourceActor.getUAL());}
			    else {sourceRef = null;}
			    if (sourceActor != null) {
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual!=null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());
			      activateGC();
			    }
			    createRemotely(__uan, __ual, "concurrent.GraphStats", sourceRef);
			  }

			  // local creation
			  else {
			    State state = new State(__uan, __ual);

			    //assume the reference is weak
			    muteGC();

			    //the source actor is  the startup actor
			    if (sourceActor == null) {
			      state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			    }

			    //the souce actor is a normal actor
			    else if (sourceActor instanceof UniversalActor.State) {

			      // this reference is part of garbage collection
			      activateGC();

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());

			      /* Garbage collection registration:
			       * register 'this reference' in sourceActor's forward list @
			       * register 'this reference' in the forward acquaintance's inverse list
			       */
			      String inverseRefString=null;
			      if (sourceActor.getUAN()!=null) {inverseRefString=sourceActor.getUAN().toString();}
			      else if (sourceActor.getUAL()!=null) {inverseRefString=sourceActor.getUAL().toString();}
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual != null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}
			      else {sourceActor.getActorMemory().getForwardList().putReference(state.getUAL());}

			      //put the inverse reference information in the actormemory
			      if (inverseRefString!=null) state.getActorMemory().getInverseList().putInverseReference(inverseRefString);
			    }
			    state.updateSelf(this);
			    ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(), state);
			    if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
			  }
		}

		//creation invoked by a remote message
		else if (obj instanceof ActorReference) {
			  ActorReference sourceRef= (ActorReference) obj;
			  State state = new State(__uan, __ual);
			  muteGC();
			  state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			  if (sourceRef.getUAN() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAN());}
			  else if (sourceRef.getUAL() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAL());}
			  state.updateSelf(this);
			  ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(),state);
			  if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
		}
	}

	public UniversalActor construct () {
		Object[] __arguments = {  };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor .State {
		public GraphStats self;
		public void updateSelf(ActorReference actorReference) {
			((GraphStats)actorReference).setUAL(getUAL());
			((GraphStats)actorReference).setUAN(getUAN());
			self = new GraphStats(false,getUAL());
			self.setUAN(getUAN());
			self.setUAL(getUAL());
			self.activateGC();
		}

		public void preAct(String[] arguments) {
			getActorMemory().getInverseList().removeInverseReference("rmsp://me",1);
			{
				Object[] __args={arguments};
				self.send( new Message(self,self, "act", __args, null,null,false) );
			}
		}

		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "concurrent.GraphStats$State" );
			addMethodsForClasses();
		}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Exception exception = null;

			if (matches != null) {
				if (!message.getMethodName().equals("die")) {activateArgsGC(message);}
				for (int i = 0; i < matches.length; i++) {
					try {
						if (matches[i].getParameterTypes().length != message.getArguments().length) continue;
						returnValue = matches[i].invoke(this, message.getArguments());
					} catch (Exception e) {
						if (e.getCause() instanceof CurrentContinuationException) {
							sendGeneratedMessages();
							return;
						} else if (e instanceof InvocationTargetException) {
							sendGeneratedMessages();
							exception = (Exception)e.getCause();
							break;
						} else {
							continue;
						}
					}
					sendGeneratedMessages();
					currentMessage.resolveContinuations(returnValue);
					return;
				}
			}

			System.err.println("Message processing exception:");
			if (message.getSource() != null) {
				System.err.println("\tSent by: " + message.getSource().toString());
			} else System.err.println("\tSent by: unknown");
			System.err.println("\tReceived by actor: " + toString());
			System.err.println("\tMessage: " + message.toString());
			if (exception == null) {
				if (matches == null) {
					System.err.println("\tNo methods with the same name found.");
					return;
				}
				System.err.println("\tDid not match any of the following: ");
				for (int i = 0; i < matches.length; i++) {
					System.err.print("\t\tMethod: " + matches[i].getName() + "( ");
					Class[] parTypes = matches[i].getParameterTypes();
					for (int j = 0; j < parTypes.length; j++) {
						System.err.print(parTypes[j].getName() + " ");
					}
					System.err.println(")");
				}
			} else {
				System.err.println("\tThrew exception: " + exception);
				exception.printStackTrace();
			}
		}

		Hashtable graphColors;
		public void construct(){
			graphColors = new Hashtable();
		}
		public String mergeString(String str1, String str2, String str3) {
			return str1+str2+str3;
		}
		public String WriteOutput() {
			Enumeration colors = graphColors.keys();
			{
				// standardOutput<-println("HEIWQJIIWDOOQWO")
				{
					Object _arguments[] = { "HEIWQJIIWDOOQWO" };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			String output = new String("");
			while (colors.hasMoreElements()) {
				String key = (String)colors.nextElement();
				Integer nodes = ((Integer[])graphColors.get(key))[0];
				Integer nodeDegree = ((Integer[])graphColors.get(key))[1];
				output.concat(key+", "+nodes+", "+nodeDegree+"\n");
				{
					// standardOutput<-println(output)
					{
						Object _arguments[] = { output };
						Message message = new Message( self, standardOutput, "println", _arguments, null, null );
						__messages.add( message );
					}
				}
			}
			return "";
		}
		public void addToGraph(Object[] results) {
			for (int i = 0; i<results.length; i++){
				Enumeration colors = ((Hashtable)results[i]).keys();
				while (colors.hasMoreElements()) {
					String key = (String)colors.nextElement();
					{
						// standardOutput<-println(key)
						{
							Object _arguments[] = { key };
							Message message = new Message( self, standardOutput, "println", _arguments, null, null );
							__messages.add( message );
						}
					}
					Object nodes_Obj = ((Hashtable)results[i]).get(key);
					Integer nodes = ((Integer[])nodes_Obj)[0];
					Integer nodeDegree = ((Integer[])nodes_Obj)[1];
					{
						// standardOutput<-println(nodes)
						{
							Object _arguments[] = { nodes };
							Message message = new Message( self, standardOutput, "println", _arguments, null, null );
							__messages.add( message );
						}
					}
					{
						// standardOutput<-println(nodeDegree)
						{
							Object _arguments[] = { nodeDegree };
							Message message = new Message( self, standardOutput, "println", _arguments, null, null );
							__messages.add( message );
						}
					}
					Integer[] info = new Integer[2];
					info[0] = nodes;
					info[1] = nodeDegree;
					if (graphColors.containsKey(key)) {{
						info[0] = info[0]+((Integer[])graphColors.get(key))[0];
						info[1] = info[1]+((Integer[])graphColors.get(key))[1];
						graphColors.put(key, info);
						{
							// standardOutput<-println(key+info[0]+info[1])
							{
								Object _arguments[] = { key+info[0]+info[1] };
								Message message = new Message( self, standardOutput, "println", _arguments, null, null );
								__messages.add( message );
							}
						}
					}
}					else {{
						graphColors.put(key, info);
					}
}				}
			}
		}
		public void CalculateColors(String filePath) {
			FileParser fileparser = new FileParser(filePath);
			Graph testGraph = fileparser.createGraph();
			Partition[] actors = new Partition[testGraph.getTotalPartition()];
			for (Integer i = 0; i<testGraph.getTotalPartition(); ++i){
				Graph currentPartition = testGraph.getPartitionGraph(i);
				currentPartition.printGraph();
				actors[i] = ((Partition)new Partition(this).construct(currentPartition));
			}
			{
				// standardOutput<-println(">>>>>>Starting the computation")
				{
					Object _arguments[] = { ">>>>>>Starting the computation" };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			{
				Token token_2_0 = new Token();
				// join block
				token_2_0.setJoinDirector();
				for (int i = 0; i<testGraph.getTotalPartition(); i++){
					{
						Token token_4_0 = new Token();
						// actors[i]<-calculateTotalDegreesAndNodesInColors()
						{
							Object _arguments[] = {  };
							Message message = new Message( self, actors[i], "calculateTotalDegreesAndNodesInColors", _arguments, null, token_4_0 );
							__messages.add( message );
						}
						// actors[i]<-OutputColorInformation()
						{
							Object _arguments[] = {  };
							Message message = new Message( self, actors[i], "OutputColorInformation", _arguments, token_4_0, token_2_0 );
							__messages.add( message );
						}
					}
				}
				addJoinToken(token_2_0);
				// addToGraph(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "addToGraph", _arguments, token_2_0, currentMessage.getContinuationToken() );
					__messages.add( message );
				}
				throw new CurrentContinuationException();
			}
		}
		public void act(String[] args) {
			int argc = args.length;
			if (argc==3) {{
				{
					Token token_3_0 = new Token();
					// CalculateColors(args[0])
					{
						Object _arguments[] = { args[0] };
						Message message = new Message( self, self, "CalculateColors", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// WriteOutput()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "WriteOutput", _arguments, token_3_0, null );
						__messages.add( message );
					}
				}
			}
}			else {{
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					Token token_3_2 = new Token();
					// standardOutput<-println("What's your name?")
					{
						Object _arguments[] = { "What's your name?" };
						Message message = new Message( self, standardOutput, "println", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// standardInput<-readLine()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, standardInput, "readLine", _arguments, token_3_0, token_3_1 );
						__messages.add( message );
					}
					// ((GraphStats)self)<-mergeString("Hi, ", token, ". Nice to meet you!")
					{
						Object _arguments[] = { "Hi, ", token_3_1, ". Nice to meet you!" };
						Message message = new Message( self, ((GraphStats)self), "mergeString", _arguments, token_3_1, token_3_2 );
						__messages.add( message );
					}
					// standardOutput<-println(token)
					{
						Object _arguments[] = { token_3_2 };
						Message message = new Message( self, standardOutput, "println", _arguments, token_3_2, null );
						__messages.add( message );
					}
				}
			}
}		}
	}
}